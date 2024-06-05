package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.GridMovable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;


public abstract class GridMovableController<T extends GridMovable>
{
    
    
    public abstract Tile moveTo(Tile tile);
    
    
    public abstract GridMap getGridMap();
    
    
    public abstract T getGridMovable();
    
    
    public abstract Tile getPosition();
}
