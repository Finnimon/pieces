package com.gitgud.utility.gsonSerialization;

import com.gitgud.model.gameObjects.Faction;
import com.gitgud.model.gameObjects.FightAgentType;
import com.gitgud.model.gameObjects.gridMovable.Agent;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.google.gson.*;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;


public class FightAgentDeserializer implements JsonDeserializer<FightAgent>
{
    private static final String TYPE = "type";

    private static final String FACTION = "faction";

    @Override
    public FightAgent deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();

        FightAgent fightAgent = SilentObjectCreator.create(FightAgent.class);

        Field[] fields = fightAgent.getClass().getDeclaredFields();

        for (Field field : fields)
        {
            field.setAccessible(true);

            try
            {
                if (field.getName().equals(TYPE))
                {
                    field.set(fightAgent, FightAgentType.fromString(jsonObject.get(TYPE).getAsString()));
                }
                else if (field.getName().equals(FACTION))
                {
                    field.set(fightAgent, Faction.fromString(jsonObject.get(FACTION).getAsString()));
                }
                else
                {
                    field.set(fightAgent, context.deserialize(jsonObject.get(field.getName()), field.getType()));
                }

            } catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }
        return fightAgent;
    }
}