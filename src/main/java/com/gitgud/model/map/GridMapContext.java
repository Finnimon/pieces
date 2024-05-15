package com.gitgud.model.map;

import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;
import com.gitgud.model.gameObjects.GridMappable;

import java.util.HashMap;


public class GridMapContext<T extends GridMappable>
{
    private final GridMap<T> gridMap;
    
    
    private final HashMap<T, Tile> gridMappings;
    
    
    public GridMapContext(GridMap gridMap, HashMap<T, Tile> gridMappings)
    {
        this.gridMap = gridMap;
        this.gridMappings = gridMappings;
    }
    
    
    public GridMap<T> getGridMap()
    {
        return gridMap;
    }
    
    
    public HashMap<T, Tile> getGridMappings()
    {
        return gridMappings;
    }
}
