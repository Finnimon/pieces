package com.gitgud.utility.gsonSerialization;


import com.gitgud.model.fight.Spell;
import com.gitgud.model.fight.SpellType;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.modification.Modifier;
import com.gitgud.utility.modification.fightAgent.FightAgentAttackModifier;
import com.gitgud.utility.modification.fightAgent.FightAgentModifier;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class SpellSerializer implements JsonSerializer<Spell>, JsonDeserializer<Spell>
{
    private static final String TYPE = "type";
    private static final String MODIFIER = "modifier";
    private static final String ATTACK = "FightAgentAttackModifier";
    private static final String DEFENCE = "FightAgentDefenceModifier";
    private static final String HEALTH = "FightAgentHealthModifier";
    private static final String MANA = "FightAgentManaModifier";

    @Override
    public JsonElement serialize (Spell src, Type type, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        Field[] fields = src.getClass().getDeclaredFields();

        try
        {
            for (Field field : fields)
            {
                field.setAccessible(true);

                switch (field.getName())
                {
                    case TYPE:
                        jsonObject.add(TYPE, context.serialize(src.getType().getAsString()));
                        break;

                    case MODIFIER:
                        serializeModifiers(jsonObject, src, context);
                        break;

                    default:
                        jsonObject.add(field.getName(), context.serialize(field.get(src)));
                }
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }


    private void serializeModifiers (JsonObject jsonObject, Spell src, JsonSerializationContext context)
    {
        JsonObject modifierCollection = new JsonObject();

        Gson gson = new Gson();

        for (Modifier<FightAgent> modifier : src.getModifier().getModifiers())
        {
            switch (modifier.getClass().getSimpleName())
            {
                case ATTACK :
                    modifierCollection.add(ATTACK, gson.toJsonTree(modifier));
                    break;

                case DEFENCE :
                    modifierCollection.add(DEFENCE, gson.toJsonTree(modifier));
                    break;

                case HEALTH :
                    modifierCollection.add(HEALTH, gson.toJsonTree(modifier));
                    break;

                case MANA :
                    modifierCollection.add(MANA, gson.toJsonTree(modifier));
                    break;
            }
        }

        jsonObject.add(MODIFIER, modifierCollection);
    }

    @Override
    public Spell deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException
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
                        deserializeModifiers(jsonObject, spell, field, context);
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

    private void deserializeModifiers (JsonObject src, Spell spell, Field field, JsonDeserializationContext context) throws IllegalAccessException
    {
        Collection<Modifier<FightAgent>> collectionOfModifiers = new ArrayList<>();

        JsonObject modifiers = src.get(MODIFIER).getAsJsonObject();

        for (String modifier : modifiers.keySet())
        {
            collectionOfModifiers.add(context.deserialize(modifiers.get(modifier), Modifier.class));
        }

        field.set(spell, new FightAgentModifier(collectionOfModifiers));
    }
}
