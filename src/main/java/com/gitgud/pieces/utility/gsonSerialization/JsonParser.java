package com.gitgud.pieces.utility.gsonSerialization;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.github.ruediste.polymorphicGson.GsonPolymorphAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import org.hildan.fxgson.FxGson;


public class JsonParser
{
    private static JsonParser instance = null;
    
    
    private final Gson gson;
    
    
    private JsonParser()
    {
        GsonBuilder gsonBuilder=FxGson.fullBuilder();
        prepareBuilder(gsonBuilder);
        gson = gsonBuilder.create();
    }
    
    private void prepareBuilder(GsonBuilder gsonBuilder)
    {
        GsonPolymorphAdapter gameObjectTypeAdapterFactory= new GsonPolymorphAdapter(
                GsonPolymorphAdapter.PolymorphStyle.TYPE_PROPERTY,
                GameObject.class.getClassLoader(),
                "com.gitgud");
//
//        GsonPolymorphAdapter fightAgentTypeAdapterFactory= new GsonPolymorphAdapter(
//                GsonPolymorphAdapter.PolymorphStyle.TYPE_PROPERTY,
//                GameObject.class.getClassLoader(),
//                "com.gitgud.pieces.model.gameobjects.agents");
        gsonBuilder.setPrettyPrinting().enableComplexMapKeySerialization();
        gsonBuilder.registerTypeAdapterFactory(gameObjectTypeAdapterFactory);
//        gsonBuilder.registerTypeAdapterFactory(fightAgentTypeAdapterFactory);
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
