package com.gitgud.engine.model.map;


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
    /**
     * If the terrain is traversable for the {@link com.gitgud.engine.model.gameobjects.GridMovable} and can be
     * occupied by any {@link com.gitgud.engine.model.gameobjects.GridMappable}
     *
     * @return Whether terrainType={@link TerrainType#TRAVERSABLE}
     */
    public boolean isTraversable()
    {
        return terrainType == TerrainType.TRAVERSABLE;
    }
}
