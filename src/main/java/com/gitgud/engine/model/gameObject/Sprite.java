package com.gitgud.engine.model.gameObject;

import java.io.File;
import java.net.MalformedURLException;


public interface Sprite
{
    String DOT_PNG=".png";
    String DOT_JPG=".jpg";
    String DOT_JPEG=".jpeg";
    String DOT_GIF=".gif";
    String DOT_SVG=".svg";
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
    
    
    static String urlFromFilePath(String filePath) throws MalformedURLException
    {
        return new File(filePath).toURI().toURL().toString();
    }
}
