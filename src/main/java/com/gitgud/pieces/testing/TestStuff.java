package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.TerrainType;
import com.gitgud.pieces.utility.Core;


public class TestStuff
{
    public static <T extends GridMappable> GridMap<T> getTestMap(int width, int height)
    {
        TerrainType[][] terrainTypes = new TerrainType[height][width];
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                terrainTypes[y][x] = TerrainType.values()[(x * y) % 2];
            }
        }
        
        return GridMap.create(terrainTypes);
    }
    
    
    public static boolean[][] booleanArray(int width, int height)
    {
        boolean[][] array = new boolean[height][width];
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                array[y][x] = Core.roll(0.9f);
            }
        }
        
        return array;
    }
    
}
