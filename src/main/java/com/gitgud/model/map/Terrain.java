package com.gitgud.model.map;


/**
 * This Record Contains the {@link TerrainType} of the {@link Tile}
 *
 * @Author: Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public record Terrain(TerrainType terrainType)
{
    public boolean isTraversable()
    {
        return terrainType == TerrainType.TRAVERSABLE;
    }
}
