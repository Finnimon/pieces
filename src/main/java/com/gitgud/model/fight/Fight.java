package com.gitgud.model.fight;

import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;
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
    private final GridMap<FightAgentFL> gridMap;
    
    
    public Fight(GridMap<FightAgentFL> gridMap)
    {
        this.gridMap = gridMap;
    }
    
    
    public GridMap<FightAgentFL> getGridMap()
    {
        return gridMap;
    }
}
