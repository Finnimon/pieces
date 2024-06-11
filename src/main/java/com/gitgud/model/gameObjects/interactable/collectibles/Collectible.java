package com.gitgud.model.gameObjects.interactable.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.interactable.Interactable;


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
        gridMap.nodeSet().stream().filter(key->gridMap.get(key) == this).findFirst().ifPresent(tile -> gridMap.put(tile, null));
    }
    
    
}
