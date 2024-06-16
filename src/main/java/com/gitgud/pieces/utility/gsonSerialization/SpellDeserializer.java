package com.gitgud.pieces.utility.gsonSerialization;


import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.fight.SpellType;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.utility.modification.fightAgent.*;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class SpellDeserializer implements JsonDeserializer<Spell>
{
    private static final String TYPE = "type";
    private static final String MODIFIER = "modifier";
    private static final String ATTACK = "FightAgentAttackModifier";
    private static final String DEFENCE = "FightAgentDefenceModifier";
    private static final String HEALTH = "FightAgentHealthModifier";
    private static final String MANA = "FightAgentManaModifier";

    @Override
    public Spell deserialize (JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = src.getAsJsonObject();

        Spell spell = SilentObjectCreator.create(Spell.class);

        Field[] fields = spell.getClass().getDeclaredFields();

        for (Field field : fields)
        {
            field.setAccessible(true);

            try
            {
                switch (field.getName())
                {
                    case TYPE :
                        field.set(spell, SpellType.fromString(jsonObject.get(TYPE).getAsString()));
                        break;

                    case MODIFIER :
                        deserializeModifiers(jsonObject, spell, field);
                        break;

                    default :
                        field.set(spell, context.deserialize(jsonObject.get(field.getName()), field.getType()));
                }
            } catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }

        return spell;
    }


    private void deserializeModifiers (JsonObject src, Spell spell, Field field) throws IllegalAccessException
    {
        Collection<Modifier<FightAgent>> collectionOfModifiers = new ArrayList<>();

        JsonObject modifiers = src.get(MODIFIER).getAsJsonObject();

        Gson gson = new Gson();

        for (String modifier : modifiers.keySet())
        {
            switch (modifier)
            {
                case ATTACK :
                    collectionOfModifiers.add(gson.fromJson(modifiers.get(modifier), FightAgentAttackModifier.class));
                    break;

                case DEFENCE :
                    collectionOfModifiers.add(gson.fromJson(modifiers.get(modifier), FightAgentDefenceModifier.class));
                    break;

                case HEALTH :
                    collectionOfModifiers.add(gson.fromJson(modifiers.get(modifier), FightAgentHealthModifier.class));
                    break;

                case MANA :
                    collectionOfModifiers.add(gson.fromJson(modifiers.get(modifier), FightAgentManaModifier.class));
                    break;
            }
        }

        field.set(spell, new FightAgentModifier(collectionOfModifiers));
    }
}