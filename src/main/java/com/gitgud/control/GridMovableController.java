package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.GridMovable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;
import com.gitgud.utility.services.GridMapServices;

import java.util.ArrayList;


public abstract class GridMovableController<T extends GridMovable>
{
    public ArrayList<Tile> getTilesInMovementRange()
    {
        GridMap gridMap = getGridMap();
        int movementRange = getGridMovable().getMovementRange();
        Tile position = getPosition();
        return GridMapServices.getInRange(gridMap, position, movementRange);
    }
    
    
    public abstract Tile moveTo(Tile tile);
    
    
    public boolean canMoveTo(Tile tile)
    {
        Tile position = getPosition();
        
        GridMap gridMap = getGridMap();
        int positionIndex = gridMap.getTileIndex(position);
        int tileIndex = gridMap.getTileIndex(tile);
        
        
        return gridMap.getAdjecancyMatrix()[positionIndex][tileIndex]<= getGridMovable().getMovementRange();
    }
    
    
    public abstract GridMap getGridMap();
    
    
    public abstract T getGridMovable();
    
    
    public abstract Tile getPosition();
}
