package com.gitgud.pieces.utility.gsonSerializationDeprecated;

import com.gitgud.engine.model.map.Terrain;
import com.gitgud.engine.model.map.Tile;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Deprecated
public class TileSerializer implements JsonSerializer<Tile>, JsonDeserializer<Tile>
{
    private static final String X = "x";
    
    
    private static final String Y = "y";
    
    
    private static final String TERRAIN = "terrain";
    
    
    private static final String INDEX = "index";
    
    
    @Override
    public Tile deserialize(JsonElement src, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = src.getAsJsonObject();
        
        int x = context.deserialize(jsonObject.get(X), Integer.class);
        int y = context.deserialize(jsonObject.get(Y), Integer.class);
        Terrain terrain = context.deserialize(jsonObject.get(TERRAIN), Terrain.class);
        int index = context.deserialize(jsonObject.get(INDEX), Integer.class);
        
        return new Tile(x, y, terrain, index);
    }
    
    
    @Override
    public JsonElement serialize(Tile src, Type type, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        
        List<Field> fields = new ArrayList<>(List.of(src.getClass().getDeclaredFields()));
        
        jsonObject.add(X, context.serialize(src.getX(), Double.class));
        jsonObject.add(Y, context.serialize(src.getY(), Double.class));
        
        for (Field field : fields)
        {
            field.setAccessible(true);
            
            try
            {
                jsonObject.add(field.getName(), context.serialize(field.get(src)));
            }
            catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }
        
        return jsonObject;
    }
}
