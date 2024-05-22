package com.gitgud.model.gameObjects;

import java.io.File;
import java.net.MalformedURLException;


public interface Sprite
{
    String getSpriteFilePath();
    
    
    public default String getSpriteUrl()
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
    
    
    public static String urlFromFilePath(String filePath) throws MalformedURLException
    {
        return new File(filePath).toURI().toURL().toString();
    }
}
