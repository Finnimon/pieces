package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.gameobjects.Faction;

import java.util.List;


public class PlayerReborn extends CityBuilding
{
    private static final String DESCRIPTION = "Rebirth your player here.";
    
    
    private static final String NAME = "Player Reborn";
    
    
    private Faction faction;
    
    
    public PlayerReborn()
    {
        this(Faction.MONOCHROME);
    }
    
    
    public PlayerReborn(Faction faction)
    {
        this(NAME, DESCRIPTION, STARTING_LEVEL, faction);
    }
    
    
    private PlayerReborn(String name, String description, int level, Faction faction)
    {
        super(name, description, level);
        this.faction = faction;
    }
    
    
    public void rebirth(Faction faction)
    {
        this.faction = faction;
    }
    
    
    public List<Faction> getAvailableFactions()
    {
        return List.of(Faction.values()).subList(0, getLevel());
    }
    
    
    @Override
    public int levelUp()
    {
        if (getLevel() >= Faction.values().length)
        {
            return getLevel();
        }
        
        return super.levelUp();
    }
}
