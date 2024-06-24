package com.gitgud.pieces.utility.gsonSerialization;



import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.fight.SpellType;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
                    case TYPE :
                        jsonObject.add(TYPE, context.serialize(src.getType().getAsString()));
                        break;

                    case MODIFIER :
                        jsonObject.add(MODIFIER, context.serialize(src.getModifier(), com.gitgud.engine.utility.modification.Modifier.class));
                        break;

                    default :
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
                        field.set(spell, context.deserialize(jsonObject.get(MODIFIER), com.gitgud.engine.utility.modification.Modifier.class));
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
}
