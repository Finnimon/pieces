package com.gitgud.utility.gsonSerialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Field;
import java.lang.reflect.Type;


public class GenericSerializer<T> implements JsonSerializer<T>
{
    
    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        Field[] fields = src.getClass().getDeclaredFields();
        
        try
        {
            for (Field field : fields)
            {
                field.setAccessible(true); // Access private fields
                Object value = field.get(src);
                jsonObject.add(field.getName(), context.serialize(value));
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException("Error during serialization", e);
        }
        
        
        return jsonObject;
    }
    
    
}