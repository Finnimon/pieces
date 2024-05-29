package com.gitgud.model.fight;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMap;


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
    
    
    public Fight(GridMap<FightAgent> gridMap)
    {
        this.gridMap = gridMap;
    }
    
    
    public GridMap<FightAgent> getGridMap()
    {
        return gridMap;
    }
}
