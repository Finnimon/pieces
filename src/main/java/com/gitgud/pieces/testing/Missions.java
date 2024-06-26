package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;


public interface Missions
{
    Mission FIRST = create(getTestMap(12, 12));
    
    
    public static <GOType extends GameObject> GridMap<GOType> getTestMap(int width, int height)
    {
        return GridMap.create(TestStuff.booleanArray(width, height));
    }
    
    
    private static Mission create(GridMap<GameObject> gridMap)
    {
        Tile startingPosition = gridMap.verticeSet().stream().filter(tile -> tile.getTerrain().isTraversable()).findFirst().get();
        return new Mission(gridMap, startingPosition, new FightAgent[0]);
    }
}
