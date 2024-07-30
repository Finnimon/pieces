package com.gitgud.pieces.utility.gsonSerializationDeprecated;

import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightAgentCollectible;
import com.google.gson.*;

import java.lang.reflect.Type;


public class FightAgentCollectableSerializer
        implements JsonSerializer<FightAgentCollectible>, JsonDeserializer<FightAgentCollectible>
{
    @Override
    public FightAgentCollectible deserialize(JsonElement src, Type type, JsonDeserializationContext context)
            throws JsonParseException
    {
        return new FightAgentCollectible(context.deserialize(src, FightAgent.class));
    }
    
    
    @Override
    public JsonElement serialize(FightAgentCollectible src, Type typeOfSrc, JsonSerializationContext context)
    {
        return context.serialize(src.getFightAgent(), FightAgent.class);
    }
}
