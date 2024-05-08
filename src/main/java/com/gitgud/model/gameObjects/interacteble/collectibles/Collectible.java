package com.gitgud.model.gameObjects.interacteble.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.interacteble.Interacteble;
import com.gitgud.model.map.GridMapContext;


public interface Collectible extends Interacteble
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
        removeFromMap(missionController.getMission());
    }
    
    
    public void addToInventory(MissionController missionController);
    
    
    public default boolean isCollectionPossible(MissionController missionController)
    {
        return true;
    }
    
    
    private void removeFromMap(GridMapContext<Interacteble> gridMapContext)
    {
        gridMapContext.getGridMappings().remove(this);
    }
    
    
}
