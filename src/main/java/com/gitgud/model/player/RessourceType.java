package com.gitgud.model.player;

public enum RessourceType
{
    IRON,
    COPPER,
    SILVER,
    GOLD,
    PLATINUM,
    REDSTONE;
    
    public String getSpriteUrl()
    {
        return "com\\gitgud\\resources\\sprites\\resources\\" + this.name().toLowerCase().trim() + ".png";
    }
}
