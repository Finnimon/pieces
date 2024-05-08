package com.gitgud.model.mission;

import com.gitgud.model.mission.map.GridMap;
import com.gitgud.model.mission.map.Tile;
import com.gitgud.utility.interfaces.GridMappable;

import java.util.HashMap;


public class GridMapContext<T extends GridMappable>
{
    private final GridMap gridMap;
    
    
    private final HashMap<T, Tile> gridMappings;
    
    
    public GridMapContext(GridMap gridMap, HashMap<T, Tile> gridMappings)
    {
        this.gridMap = gridMap;
        this.gridMappings = gridMappings;
    }
    
    
    public GridMap getGridMap()
    {
        return gridMap;
    }
    
    
    public HashMap<T, Tile> getGridMappings()
    {
        return gridMappings;
    }
}
