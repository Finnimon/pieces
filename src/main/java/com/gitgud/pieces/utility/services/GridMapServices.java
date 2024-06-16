package com.gitgud.pieces.utility.services;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;

import java.util.ArrayList;


public class GridMapServices
{
    private GridMapServices()
    {
    }
    
    
    public static ArrayList<Tile> getInRange(GridMap gridMap, Tile position, int Range)
    {
        int tileIndex = gridMap.getTileIndex(position);
        float[] adjecancies = gridMap.getAdjecancyMatrix()[tileIndex];
        
        ArrayList<Tile> inRangeTiles = new ArrayList<>();
        
        for (int index = 0; index < adjecancies.length; index++)
        {
            if (adjecancies[index] <= Range)
            {
                inRangeTiles.add(gridMap.getTileByIndex(index));
            }
        }
        inRangeTiles.remove(position);
        
        
        return inRangeTiles;
    }
}
