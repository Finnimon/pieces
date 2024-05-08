package com.gitgud.utility.interfaces;

import com.gitgud.control.MissionController;
import com.gitgud.model.mission.GridMapContext;


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
