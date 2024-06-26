package com.gitgud.engine.model.gameobjects.interactable;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;


public interface Collectible<AAType extends ActionAwaitingController<Mission, GameObject, MissionRender>> extends Interactable<AAType>
{
    @Override
    default void interact(AAType missionController)
    {
        collect(missionController);
    }
    
    
    private void collect(AAType missionController)
    {
        if (!isCollectionPossible())
        {
            return;
        }
        
        
        addToInventory();
        removeFromMap(missionController.getModel().getGridMap());
    }
    
    
    void addToInventory();
    
    
    default boolean isCollectionPossible()
    {
        return true;
    }
    
    
    private void removeFromMap(GridMap gridMap)
    {
        Tile vertex = (Tile) gridMap.getVertex( this);
        
        if (vertex == null)
        {
            return;
        }
        
        
        gridMap.clearVertex(vertex);
    }
    
    
}
