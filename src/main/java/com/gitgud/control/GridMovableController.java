package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.GridMovable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;

import java.util.HashMap;


public class GridMovableController <T extends GridMovable>
{
    private final GridMap<T> gridMap;
    
    
    private final T gridMovable;
    
  
    private final Tile position;
    
    
    public GridMovableController(GridMap<T> gridMap, T gridMovable, Tile position)
    {
        this.gridMap = gridMap;
        this.gridMovable = gridMovable;
        this.position = position;
    }
    
    public  GridMovableController(GridMap<T> gridMap,T gridMovable)
    {
        this.gridMap = gridMap;
        this.gridMovable = gridMovable;
        this.position = gridMap.getTile(gridMovable);
    }
    
    
    public Tile moveTo(Tile tile)
    {
        Tile oldTile = getPosition();
        
        HashMap<Tile,T> graph = gridMap.getGraph();
        
        graph.put(tile, getGridMovable());
        graph.put(oldTile, null);
        
        return oldTile;
    }
    
    
    private boolean canMoveTo(Tile tile)
    {
        return false;
    }
    
    
    public GridMap getGridMap()
    {
        return gridMap;
    }
    
    
    public T getGridMovable()
    {
        return gridMovable;
    }
    
    
    public Tile getPosition()
    {
        return position;
    }
}
