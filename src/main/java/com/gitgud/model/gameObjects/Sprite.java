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
    
    
    private String urlFromFilePath(String path) throws MalformedURLException
    {
        return new File(path).toURI().toURL().toString();
    }
}
