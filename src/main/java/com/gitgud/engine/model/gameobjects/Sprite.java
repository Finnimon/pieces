package com.gitgud.engine.model.gameobjects;

import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;


public interface Sprite
{
    String DOT_PNG = ".png";
    
    
    String DOT_JPG = ".jpg";
    
    
    String DOT_JPEG = ".jpeg";
    
    
    String DOT_GIF = ".gif";
    
    
    String DOT_SVG = ".svg";
    
    
    HashMap<String, Image> loadedImages = new HashMap<>();
    
    
    static String urlFromFilePath(String filePath) throws MalformedURLException
    {
        return new File(filePath).toURI().toURL().toString();
    }
    
    
    String getSpriteFilePath();
    
    
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
        
        
        return null;
    }
    
    
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
}
