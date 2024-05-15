package com.gitgud.model.map;

import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;
import com.gitgud.model.gameObjects.GridMappable;

import java.util.HashMap;


public class GridMapContext<T extends GridMappable>
{
    private final GridMap<T> gridMap;
    
    
    
    public GridMapContext(GridMap gridMap)
    {
        this.gridMap = gridMap;
    }
    
    
    public GridMap<T> getGridMap()
    {
        return gridMap;
    }
    
}
