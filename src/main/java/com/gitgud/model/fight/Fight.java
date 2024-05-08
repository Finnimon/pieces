package com.gitgud.model.fight;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMapContext;
import com.gitgud.model.map.FightGridMap;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;

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
    
    public Fight(FightGridMap gridMap, HashMap<FightAgent, Tile> gameObjectMappings)
    {
        super(gridMap, gameObjectMappings);
    }
    
    
    
}
