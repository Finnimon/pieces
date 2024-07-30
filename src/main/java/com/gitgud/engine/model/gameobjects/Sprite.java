package com.gitgud.engine.model.gameobjects;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;


/**
 * Interface for all objects that have a sprite.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 25.04.2024
 * @Version: 2.0
 */
public interface Sprite
{
    String DOT_PNG = ".png";
    
    
    String DOT_JPG = ".jpg";
    
    
    String DOT_JPEG = ".jpeg";
    
    
    String DOT_GIF = ".gif";
    
    
    String DOT_SVG = ".svg";
    
    
    /**
     * <p>All images that have so far been loaded.
     * <p>While this slows down the original loading of images, it saves memory and allows for faster loading of the
     * same image again.
     */
    HashMap<String, Image> loadedImages = new HashMap<>();
    
    
    @NotNull
    default Image getSprite()
    {
        String spriteUrl = getSpriteUrl();
        Image sprite = loadedImages.get(spriteUrl);
        
        if (sprite != null)
        {
            return sprite;
        }
        sprite = new Image(spriteUrl);
        loadedImages.put(spriteUrl, sprite);
        
        return sprite;
    }
    
    
    /**
     * Gets the URL for the sprite using the {@link #getSpriteFilePath()}.
     *
     * @return The intact URL of the sprite.
     */
    @NotNull
    default String getSpriteUrl()
    {
        try
        {
            return urlFromFilePath(getSpriteFilePath());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        
        throw new RuntimeException("Could not load sprite: " + getSpriteFilePath());
    }
    
    
    /**
     * Determines a URL from a FilePath.
     *
     * @param filePath The FilePath.
     * @return Whole URL of {@code filePath}.
     * @throws MalformedURLException If a protocol handler for the URL could not be found, or if some other error
     * occurred while constructing the URL
     */
    static String urlFromFilePath(String filePath) throws MalformedURLException
    {
        return new File(filePath).toURI().toURL().toString();
    }
    
    
    /**
     * Getter for an Objects sprite file path.
     * @return The sprite file path of an Object.
     */
    @NotNull String getSpriteFilePath();
}
