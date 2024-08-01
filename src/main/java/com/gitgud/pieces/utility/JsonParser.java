package com.gitgud.pieces.utility;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.github.ruediste.polymorphicGson.GsonPolymorphAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Singleton for customized the {@link com.google.gson.Gson} instance.
 *
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @since 28.07.2024
 */
public class JsonParser
{
    public static final String DOT_JSON = ".json";
    
    
    /**
     * Singleton Instance
     */
    private static JsonParser instance = null;
    
    
    /**
     * Gson Object
     */
    private final Gson gson;
    
    
    /**
     * Private Constructor that initializes the Gson Object.
     */
    private JsonParser()
    {
        GsonBuilder gsonBuilder = FxGson.fullBuilder();
        customizeBuilder(gsonBuilder);
        gson = gsonBuilder.create();
    }
    
    
    /**
     * Adds all custom parsing setting to the Gson Builder.
     *
     * @param gsonBuilder The Gson Builder to be customized.
     */
    private synchronized void customizeBuilder(GsonBuilder gsonBuilder)
    {
        // Add polymorph support
        GsonPolymorphAdapter gameObjectPolymorphAdapter =
                new GsonPolymorphAdapter(GsonPolymorphAdapter.PolymorphStyle.TYPE_PROPERTY,
                                                                                   GameObject.class.getClassLoader(),
                                                                                   "com.gitgud");
        gsonBuilder.registerTypeAdapterFactory(gameObjectPolymorphAdapter);
        // Add pretty printing and enable complex map key serialization for Graphs
        gsonBuilder.setPrettyPrinting().enableComplexMapKeySerialization();
    }
    
    
    /**
     * Singleton Getter
     *
     * @return The Singleton Instance
     */
    public static JsonParser getInstance()
    {
        if (instance == null)
        {
            instance = new JsonParser();
        }
        return instance;
    }
    
    
    /**
     * The Gson Getter.
     *
     * @return The Gson.
     */
    public Gson getGson()
    {
        return gson;
    }
    
    
    /**
     * Deserializes a JSON File into an Object of the given Class.
     *
     * @param jsonFile The File to be deserialized.
     * @param clazz    The Class of the Object to be deserialized.
     * @param <T>      The type of the Object to be deserialized.
     * @return The deserialized Object.
     * @Precondition: The File must exist and be readable. The Json must be intact.
     * @Postcondition: No Exceptions will be thrown. The returned object will be intact.
     */
    public <T> T deserializeJsonFile(File jsonFile, Class<T> clazz)
    {
        try
        {FileReader reader = getFileReader(jsonFile);
            T t = gson.fromJson(reader, clazz);
            reader.close();
            
            return t;
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("File not found: " + jsonFile, e);
        }
    }
    
    
    /**
     * Creates a File Reader for the given File.
     *
     * @param file The File to be read.
     * @return The created File Reader.
     * @throws IllegalArgumentException If the File cannot be read or found.
     */
    public FileReader getFileReader(File file)
    {
        try
        {
            return new FileReader(file);
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("File not found or unreadable: " + file, e);
        }
    }
    
    
    /**
     * Calls {@link #replaceFileContent(File, String)} with the given {@link File} and serializes the given Object
     * into it.
     *
     * @param jsonFile The File to be replaced.
     * @param object   The Object to be serialized.
     * @return True if the replacement was successful, false otherwise.
     * @see #replaceFileContent(File, String)
     */
    public boolean parseIntoJsonFile(File jsonFile, Object object)
    {
        return replaceFileContent(jsonFile, gson.toJson(object));
    }
    
    
    /**
     * Replaces/creates {@code file}'s content with {@code string}.
     *
     * @param file   The File to be replaced.
     * @param string The String to be written into the File.
     * @return True if the replacement was successful, false otherwise.
     */
    public boolean replaceFileContent(File file, String string)
    {
        file.delete();
        try
        {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(string);
            fileWriter.close();
        }
        catch (IOException e)
        {
            return false;
        }
        
        return true;
    }
}
