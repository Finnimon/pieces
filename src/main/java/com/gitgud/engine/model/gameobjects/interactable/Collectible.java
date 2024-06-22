package com.gitgud.engine.model.gameobjects.interactable;


import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;


public interface Collectible extends Interactable
{
    @Override
    default void interact(GridMap gridMap)
    {
        collect(gridMap);
    }
    
    
    private void collect(GridMap gridMap)
    {
        if (!isCollectionPossible())
        {
            return;
        }
        
        
        addToInventory();
        removeFromMap(gridMap);
    }
    
    
    void addToInventory();
    
    
    default boolean isCollectionPossible()
    {
        return true;
    }
    
    
    private void removeFromMap(GridMap<Interactable> gridMap)
    {
        Tile vertex = gridMap.getVertex(this);
        
        if (vertex == null)
        {
            return;
        }
        
        
        gridMap.place(vertex, null);
    }
    
    
}
