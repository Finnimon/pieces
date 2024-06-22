package com.gitgud.pieces.model.gameobjects;

public enum Faction
{
    MONOCHROME("monochrome"), PINK("pink"), GREEN("green");
    
    
    private final String name;
    
    
    Faction(String name)
    {
        this.name = name;
    }
    
    
    public String getAsString()
    {
        return this.name;
    }
    
    
    public static Faction fromString(String name)
    {
        for (Faction f : Faction.values())
        {
            if (f.name.equalsIgnoreCase(name))
            {
                return f;
            }
        }
        return null;
    }
}
