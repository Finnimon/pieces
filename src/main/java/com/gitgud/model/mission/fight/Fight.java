package com.gitgud.model.mission.fight;

import com.gitgud.model.gameObjects.FightAgent;
import com.gitgud.model.mission.GridMapContext;
import com.gitgud.model.mission.map.FightGridMap;
import com.gitgud.model.mission.map.GridMap;
import com.gitgud.model.mission.map.Tile;

import java.util.HashMap;


/**
 * The Object in which a Fight or online Fight Takes place
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class Fight extends GridMapContext<FightAgent>
{
    
    public Fight(GridMap gridMap, HashMap<FightAgent, Tile> gameObjectMappings)
    {
        super(gridMap, gameObjectMappings);
        if (gridMap.getClass() != FightGridMap.class)
        {
            throw new IllegalArgumentException("gridMap must be of type FightGridMap");
        }
    }
    
}
