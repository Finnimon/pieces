package com.gitgud.pieces.utility.gsonSerializationDeprecated;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.google.gson.*;

import java.lang.reflect.Type;


@Deprecated
public class GridMapSerializer implements JsonSerializer<GridMap<GridMappable>>, JsonDeserializer<GridMap<GridMappable>>
{
    @Override
    public GridMap<GridMappable> deserialize(JsonElement src, Type type, JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonArray rows = new JsonArray();
        
        for (String key : src.getAsJsonObject().keySet())
        {
            rows = src.getAsJsonObject().get(key).getAsJsonArray();
        }
        
        boolean[][] grid = context.deserialize(rows, boolean[][].class);
        
        return GridMap.create(grid);
    }
    
    
    @Override
    public JsonElement serialize(GridMap src, Type typeOfSrc, JsonSerializationContext context)
    {
        return null;
    }
}
