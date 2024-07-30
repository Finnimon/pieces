package com.gitgud.engine.model.gameobjects.interactable;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;


public interface Collectible<AAType extends ActionAwaitingController<?, GameObject, ?>> extends Interactable<AAType>
{
    @Override
    default void interact(AAType missionController)
    {
        collect(missionController);
    }
    
    
    default void collect(AAType awaiter)
    {
        if (!isCollectionPossible())
        {
            return;
        }
        
        
        addToInventory();
        removeFromMap(awaiter);
    }
    
    
    default boolean isCollectionPossible()
    {
        return true;
    }
    
    
    void addToInventory();
    
    
    private void removeFromMap(AAType awaiter)
    {
        GridMap gridMap = awaiter.getModel().getGridMap();
        Tile vertex = (Tile) gridMap.getVertex(this);
        
        if (vertex == null)
        {
            return;
        }
        
        
        gridMap.clearVertex(vertex);
        
        awaiter.getRender().getGridMapRender().removeGridMappable((GameObject) this);
    }
    
    
}
