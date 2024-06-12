package com.gitgud.utility.gsonSerialization;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FightAgentSerializer implements JsonSerializer<FightAgent> {

    private static final String TYPE = "type";

    private static final String FACTION = "faction";

    @Override
    public JsonElement serialize (FightAgent src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject jsonObject = new JsonObject();

        List<Field> fields = new ArrayList<>(List.of(src.getClass().getDeclaredFields()));

        fields.addAll(Arrays.asList(src.getClass().getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(src.getClass().getSuperclass().getSuperclass().getDeclaredFields()));

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

                    case FACTION:
                        jsonObject.add(FACTION, context.serialize(src.getFaction().getAsString()));
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
}
