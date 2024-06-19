package com.gitgud.utility.gsonSerialization;


import com.gitgud.model.fight.Spell;
import com.gitgud.model.fight.SpellType;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.modification.Modifier;
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
                        serializeFightAgentModifier(jsonObject, src, context);
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


    private void serializeFightAgentModifier (JsonObject jsonObject, Spell src, JsonSerializationContext context)
    {

    }


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
                        deserializeFightAgentModifier(jsonObject, spell, field, context);
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

    private void deserializeFightAgentModifier(JsonObject src, Spell spell, Field field, JsonDeserializationContext context) throws IllegalAccessException
    {

    }
}
