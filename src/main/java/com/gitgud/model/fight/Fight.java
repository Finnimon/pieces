package com.gitgud.model.fight;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.player.Player;

import java.util.HashMap;
import java.util.HashSet;


/**
 * The Object in which a Fight or online Fight Takes place
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class Fight
{
    //todo render
    private final GridMap<FightAgent> gridMap;
    
    
    private final HashMap<Player, HashSet<FightAgent>> ownershipMap;
    
    
    public Fight(GridMap<FightAgent> gridMap, HashMap<Player, HashSet<FightAgent>> ownershipMap)
    {
        this.gridMap = gridMap;
        this.ownershipMap = ownershipMap;
    }
    
    
    public GridMap<FightAgent> getGridMap()
    {
        return gridMap;
    }
    
    
    public HashMap<Player, HashSet<FightAgent>> getOwnershipMap()
    {
        return ownershipMap;
    }
}
