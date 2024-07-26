package com.gitgud.pieces.model.player;

import com.gitgud.engine.model.gameobjects.Sprite;


public enum ResourceType implements Sprite
{
    IRON, COPPER, SILVER, GOLD, PLATINUM, REDSTONE;
    
    
    public static final String DIR_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\player\\resourceType\\";
    
    
    public static ResourceType fromString(String string)
    {
        for (ResourceType resourceType : ResourceType.values())
        {
            if (string.equalsIgnoreCase(resourceType.toString()))
            {
                return resourceType;
            }
        }
        return null;
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return DIR_PATH + name() + DOT_PNG;
    }
    
    
    public String getAsString()
    {
        return this.toString();
    }
}
