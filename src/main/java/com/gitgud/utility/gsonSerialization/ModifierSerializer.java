package com.gitgud.utility.gsonSerialization;


import com.gitgud.utility.modification.Modifier;
import com.gitgud.utility.modification.fightAgent.*;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ModifierSerializer implements JsonSerializer<Modifier<Type>>, JsonDeserializer<Modifier<Type>>
{
    private static final String FIGHT_AGENT = "FightAgentModifier";
    private static final String ATTACK = "FightAgentAttackModifier";
    private static final String DEFENCE = "FightAgentDefenceModifier";
    private static final String HEALTH = "FightAgentHealthModifier";
    private static final String MANA = "FightAgentManaModifier";
    private static final String MOVEMENT = "FightAgentMovementModifier";
    private static final String DURATION = "DurableFightAgentModifier";

    @Override
    public JsonElement serialize(Modifier<Type> src, Type type, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();

        List<Field> fields = new ArrayList<>(List.of(src.getClass().getDeclaredFields()));

        fields.addAll(Arrays.asList(src.getClass().getSuperclass().getDeclaredFields()));

        try
        {
            switch (src.getClass().getSimpleName())
            {
                case FIGHT_AGENT :

            }


            for (Field field : fields)
            {
                field.setAccessible(true);

                switch ()
                jsonObject.add(field.getName(), context.serialize(field.get(src)));
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    private void serializeAllModifiers (FightAgentModifier src, JsonObject jsonObject)
    {
        JsonObject modifiers = new JsonObject();



    }

    @Override
    public Modifier<Type> deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        Modifier<Type> modifier = null;

        for (String key : src.getAsJsonObject().keySet())
        {
            switch (key)
            {
                case ATTACK :
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentAttackModifier.class);
                    break;

                case DEFENCE :
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentDefenceModifier.class);
                    break;

                case HEALTH :
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentHealthModifier.class);
                    break;

                case MANA :
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentManaModifier.class);
                    break;

                case MOVEMENT :
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentMovementModifier.class);
                    break;

                default :
                    break;
            }
        }

        return modifier;
    }
}
