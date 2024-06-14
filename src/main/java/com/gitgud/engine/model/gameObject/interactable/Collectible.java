package com.gitgud.engine.model.gameObject.interactable;

import com.gitgud.engine.model.map.GridMap;


public interface Collectible extends Interactable
{
    @Override
    public default void interact(GridMap gridMap)
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
    
    
    public void addToInventory();
    
    
    public default boolean isCollectionPossible()
    {
        return true;
    }
    
    
    private void removeFromMap(GridMap<Interactable> gridMap)
    {
        gridMap.keySet().stream().filter(key->gridMap.get(key) == this).findFirst().ifPresent(tile -> gridMap.put(tile, null));
    }
    
    
}
