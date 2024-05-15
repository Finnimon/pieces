package com.gitgud.model.player;

import java.io.File;
import java.net.MalformedURLException;


public enum ResourceType
{
    IRON, COPPER, SILVER, GOLD, PLATINUM, REDSTONE;
    
    
    public String getSpriteUrl()
    {
        try
        {
            return new File(
                    "src\\main\\resources\\com\\gitgud\\sprites\\resources\\" + this.name().toLowerCase().trim() + ".png").toURI().toURL().toString();
            
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        
        
        return null;
    }
}
