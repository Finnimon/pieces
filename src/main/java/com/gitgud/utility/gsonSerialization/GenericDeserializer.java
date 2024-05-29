package com.gitgud.utility.gsonSerialization;

import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;


public class GenericDeserializer<T> implements JsonDeserializer<T>
{
    
    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        Class<?> clazz = (Class<?>) typeOfT;
        
        try
        {
            T instance = (T) clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            
            for (Field field : fields)
            {
                field.setAccessible(true); // Access private fields
                JsonElement fieldElement = jsonObject.get(field.getName());
                if (fieldElement == null)
                {
                    continue;
                }
                Object fieldValue = context.deserialize(fieldElement, field.getType());
                field.set(instance, fieldValue);
            }
            
            return instance;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error during deserialization", e);
        }
    }
}