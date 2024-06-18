package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Terrain;
import com.gitgud.engine.model.map.TerrainType;
import com.gitgud.engine.model.map.Tile;

import java.util.HashSet;


public class TestStuff
{
    public static <T extends GridMappable> GridMap<T> getTestMap(int width, int height)
    {
        HashSet<Tile> tiles = new HashSet<>();
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                tiles.add(Tile.create(x, y, new Terrain(TerrainType.values()[x * y % 2]), width));
            }
        }
        System.out.println(tiles.size());
        return new GridMap<T>(width, height, tiles);
    }
}
