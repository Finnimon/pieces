package com.gitgud.pieces.utility.gsonSerialization;

import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightAgentCollectable;
import com.google.gson.*;

import java.lang.reflect.Type;


public class FightAgentCollectableSerializer implements JsonSerializer<FightAgentCollectable>, JsonDeserializer<FightAgentCollectable>
{
    @Override
    public FightAgentCollectable deserialize(JsonElement src, Type type,
                                             JsonDeserializationContext context) throws JsonParseException
    {
        return new FightAgentCollectable(context.deserialize(src, FightAgent.class));
    }
    
    
    @Override
    public JsonElement serialize(FightAgentCollectable src, Type typeOfSrc, JsonSerializationContext context)
    {
        return context.serialize(src.getFightFigure(), FightAgent.class);
    }
}
