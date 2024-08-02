package com.gitgud.pieces.model.gameobjects;

import java.util.Objects;


/**
 * The Faction of a {@link com.gitgud.pieces.model.gameobjects.agents.PlayerAgent} or
 * {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
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
        for (var f : Faction.values())
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
