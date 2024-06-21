package com.gitgud.engine.control;

import com.gitgud.engine.model.gameobjects.GridMovable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;


/**
 * Controls the movement of a {@link GridMovable} on a {@link GridMap}
 *
 * @Owner: Finn L.
 * @Author: Finn L.
 * @Since: 25.05.2024
 * @Version: 2.0
 */
public abstract class GridMovableController<T extends GridMovable>
{
    
    
    public abstract Tile moveTo(Tile tile);
    
    
    public abstract GridMap getGridMap();
    
    
    public abstract T getGridMovable();
    
    
    public abstract Tile getPosition();
}
