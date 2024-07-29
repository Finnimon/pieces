package com.gitgud.pieces.model.gameobjects;

import java.util.Objects;


public enum Faction
{
    MONOCHROME("monochrome"),
    PINK("pink"),
    GREEN("green");
    
    
    public static final int TYPE_MULTIPLIER = 10;
    
    
    private final String name;
    
    
    Faction(String name)
    {
        this.name = name;
    }
    
    
    public static Faction fromString(String name)
    {
        name = name.trim().toUpperCase();
        for (Faction f : Faction.values())
        {
            if (!Objects.equals(f.name, name))
            {
                continue;
            }
            
            
            return f;
        }
        
        
        return null;
    }
    
    
    public int typeToInt()
    {
        return this.ordinal() * TYPE_MULTIPLIER;
    }
    
    
    public String getAsString()
    {
        return this.name;
    }
}
