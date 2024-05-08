package com.gitgud.model.mission.map;

/**
 * The Geographic Type of Terrain on a {@link GridMap}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public enum TerrainType
{
    //todo icon binding
    TRAVERSABLE, MOUNTAIN;
    
    
    public static TerrainType getCorrespondingTerrainType(String terrainType)
    {
        return TerrainType.values()[Integer.parseInt(terrainType)];
    }
}
