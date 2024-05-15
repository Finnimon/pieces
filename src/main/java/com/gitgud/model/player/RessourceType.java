package com.gitgud.model.player;

public enum RessourceType
{
    IRON,
    COPPER,
    SILVER,
    GOLD,
    PLATINUM,
    REDSTONE;
    
    String getSpriteUrl()
    {
        return "com/gitgud/sprites/resources/" + this.name().toLowerCase().trim() + ".png";
    }
}
