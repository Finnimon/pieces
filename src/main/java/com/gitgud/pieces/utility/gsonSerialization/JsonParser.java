package com.gitgud.pieces.utility.gsonSerialization;

import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonParser
{
    private static JsonParser instance = null;
    
    
    private final Gson gson;
    
    
    private JsonParser()
    {
        GsonBuilder builder = new GsonBuilder();
        registerAdapters(builder);
        gson = builder.create();
    }
    
    
    private void registerAdapters(GsonBuilder gsonBuilder)
    {
        gsonBuilder.registerTypeAdapter(FightAgent.class, new FightAgentSerializer());
        gsonBuilder.registerTypeAdapter(Spell.class, new SpellSerializer());
    }
    
    
    public static JsonParser getInstance()
    {
        if (instance == null)
        {
            instance = new JsonParser();
        }
        return instance;
    }
    
    
    public Gson getGson()
    {
        return gson;
    }
}
