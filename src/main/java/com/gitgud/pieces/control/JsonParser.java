package com.gitgud.pieces.control;

import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.utility.gsonSerialization.TileAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.geometry.Point2D;


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
    private void registerAdapters(GsonBuilder builder)
    {
        builder.registerTypeHierarchyAdapter(Tile.class, new TileAdapter());
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
