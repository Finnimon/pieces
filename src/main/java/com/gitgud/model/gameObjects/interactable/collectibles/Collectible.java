package com.gitgud.model.gameObjects.interactable.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.interactable.Interactable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;

import java.util.TreeMap;


public interface Collectible extends Interactable
{
    @Override
    public default void interact(MissionController missionController)
    {
        collect(missionController);
    }
    
    
    private void collect(MissionController missionController)
    {
        if (!isCollectionPossible(missionController))
        {
            return;
        }
        
        
        addToInventory(missionController);
        removeFromMap(missionController.getMission().getGridMap());
    }
    
    
    public void addToInventory(MissionController missionController);
    
    
    public default boolean isCollectionPossible(MissionController missionController)
    {
        return true;
    }
    
    
    private void removeFromMap(GridMap<Interactable> gridMap)
    {
        TreeMap<Tile, Interactable> graph = gridMap.getGraph();
        graph.keySet().stream().filter(tile->graph.get(tile) == this).findFirst().ifPresent(tile -> graph.put(tile, null));
    }
    
    
}
