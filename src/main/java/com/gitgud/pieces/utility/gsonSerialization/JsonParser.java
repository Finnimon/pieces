package com.gitgud.pieces.utility.gsonSerialization;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.github.ruediste.polymorphicGson.GsonPolymorphAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class JsonParser
{
    public static final String DOT_JSON = ".json";
    
    
    private static JsonParser instance = null;
    
    
    private final Gson gson;
    
    
    private JsonParser()
    {
        GsonBuilder gsonBuilder=FxGson.fullBuilder();
        prepareBuilder(gsonBuilder);
        gson = gsonBuilder.create();
    }
    
    private synchronized void prepareBuilder(GsonBuilder gsonBuilder)
    {
        GsonPolymorphAdapter gameObjectTypeAdapterFactory= new GsonPolymorphAdapter(
                GsonPolymorphAdapter.PolymorphStyle.TYPE_PROPERTY,
                GameObject.class.getClassLoader(),
                "com.gitgud");
        gsonBuilder.setPrettyPrinting().enableComplexMapKeySerialization();
        gsonBuilder.registerTypeAdapterFactory(gameObjectTypeAdapterFactory);
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
    
    
    public <T> T parseJson(File jsonFile, Class<T> clazz)
    {
        FileReader reader;
        try
        {
            reader = new FileReader(jsonFile);
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalArgumentException("File not found: " + jsonFile, e);
        }
        return gson.fromJson(reader, clazz);
    }
}
