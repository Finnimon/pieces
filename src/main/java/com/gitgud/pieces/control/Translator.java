package com.gitgud.pieces.control;

import com.gitgud.pieces.utility.JsonParser;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;


/**
 * Offers translation functionality for the game's ui.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @since 15.07.2024
 */
public class Translator
{
    /**
     * Filepath of the translation table.
     */
    private static final String TRANSLATION_TABLE_FILEPATH = "src\\main\\resources\\com\\gitgud\\pieces\\control" +
                                                             "\\translatingTable.json";
    
    
    /**
     * Singleton Instance to allow for faster access.
     */
    private static Translator instance;
    
    
    /**
     * The translation table.
     */
    private final JsonObject translationTable;
    
    
    /**
     * Private Singleton Constructor that loads the translation table.
     */
    private Translator()
    {
        FileReader reader = JsonParser.getInstance().getFileReader(new File(TRANSLATION_TABLE_FILEPATH));
        translationTable = com.google.gson.JsonParser.parseReader(reader).getAsJsonObject();
    }
    
    
    /**
     * Singleton pattern instance getter.
     *
     * @return The singleton instance.
     */
    public static Translator getInstance()
    {
        if (instance == null)
        {
            instance = new Translator();
        }
        return instance;
    }
    
    
    /**
     * Resets the Singleton to save memory or as a way of manual garbage collection.
     */
    public static void reset()
    {
        instance = null;
    }
    
    
    /**
     * Gets all language names available in the translation table.
     *
     * @return All language names available in the translation table.
     */
    public Collection<String> getLanguages()
    {
        return translationTable.keySet();
    }
    
    
    /**
     * Translates the given {@code key} according to {@link GameSettings#getLanguage()}.
     *
     * @param key The key of the translation.
     * @return The translation of {@code key} in {@link GameSettings#getLanguage()}.
     */
    public String translate(@NotNull String key)
    {
        return translate(GameSettings.getInstance().getLanguage(), key);
    }
    
    
    /**
     * Translates the given {@code key} in the given {@code  language}.
     *
     * @param language The target language.
     * @param key      The key of the translation.
     * @return The translation of {@code key} in {@code language}.
     */
    private String translate(@NotNull String language, @NotNull String key)
    {
        return getLanguage(language).get(key).getAsString();
    }
    
    
    /**
     * Gets the translation table for the given language.
     *
     * @param language The language to get the translation table for.
     * @return The translation table for {@code language}.
     */
    private JsonObject getLanguage(@NotNull String language)
    {
        return translationTable.get(language).getAsJsonObject();
    }
}
