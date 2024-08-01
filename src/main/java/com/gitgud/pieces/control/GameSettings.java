package com.gitgud.pieces.control;

import com.gitgud.pieces.utility.JsonParser;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.jetbrains.annotations.NotNull;

import java.io.File;


/**
 * Global GameSettings of the Game.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @since 15.07.2024
 */
public class GameSettings
{
    /**
     * The location of the previous settings file.
     */
    private static final File PREVOIUS_SETTINGS = new File(
            "src\\main\\resources\\com\\gitgud\\pieces\\control\\settings.json");
    
    
    /**
     * The singleton instance.
     */
    private static GameSettings instance = null;
    
    
    /**
     * <p>The global volume of the music.
     * <p>Range: 0.0 - 1.0
     */
    private final SimpleDoubleProperty musicVolume;
    
    
    /**
     * The language for the game.
     *
     * @see Translator#translate(String)
     */
    private final SimpleStringProperty language;
    
    
    /**
     * Private Singleton Constructor.
     *
     * @param language    The language for the game.
     * @param musicVolume The global volume of the music.
     */
    private GameSettings(String language, double musicVolume)
    {
        this.language = new SimpleStringProperty(language);
        this.musicVolume = new SimpleDoubleProperty(musicVolume);
    }
    
    
    /**
     * Singleton pattern instance getter.
     *
     * @return The singleton instance.
     */
    public static GameSettings getInstance()
    {
        if (notInitialized())
        {
            instance = JsonParser.getInstance().deserializeJsonFile(PREVOIUS_SETTINGS, GameSettings.class);
        }
        return instance;
    }
    
    
    /**
     * Determines whether the instance is initialized or not.
     *
     * @return {@code true} if the instance is initialized or else {@code false}.
     */
    private static boolean notInitialized()
    {
        return instance == null;
    }
    
    
    /**
     * Resets the singleton as manual garbage collection and saves the settings.
     */
    public static void reset()
    {
        if (notInitialized())
        {
            return;
        }
        GameSettings lastGameSettings = instance;
        JsonParser.getInstance().parseIntoJsonFile(PREVOIUS_SETTINGS, lastGameSettings);
        instance = null;
    }
    
    
    /**
     * Getter for the music volume.
     * <p>Range: 0.0 - 1.0
     *
     * @return The music volume.
     */
    public double getMusicVolume()
    {
        return musicVolume.get();
    }
    
    
    /**
     * Getter for the music volume.
     * <p>Range: 0.0 - 1.0
     *
     * @param musicVolume The new music volume.
     */
    public void setMusicVolume(double musicVolume)
    {
        this.musicVolume.set(musicVolume);
    }
    
    
    /**
     * Getter for the music volume property.
     * <p>Range: 0.0 - 1.0
     *
     * @return The music volume property.
     */
    public SimpleDoubleProperty musicVolumeProperty()
    {
        return musicVolume;
    }
    
    
    /**
     * Getter for the language settings.
     *
     * @return The language settings.
     */
    public String getLanguage()
    {
        return language.get();
    }
    
    
    /**
     * Setter for the language settings.
     *
     * @param language The new language settings.
     * @throws IllegalArgumentException If {@code language} is not in {@link Translator#getLanguages()}
     * @Precondition: {@code language} must be in {@link Translator#getLanguages()}
     * @Postcondition: The language settings will be set to {@code language}.
     */
    public void setLanguage(@NotNull String language)
    {
        if (!Translator.getInstance().getLanguages().contains(language))
        {
            throw new IllegalArgumentException("Invalid language: " + language);
        }
        this.language.set(language);
    }
    
    
    /**
     * Getter for the language settings property.
     *
     * @return The language settings property.
     */
    public SimpleStringProperty languageProperty()
    {
        return language;
    }
    
    
}
