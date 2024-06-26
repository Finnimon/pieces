package com.gitgud.engine.model.map;


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
    TRAVERSABLE, NON_TRAVERSABLE;
    
    
    public static TerrainType getCorrespondingTerrainType(String terrainTypeIndex)
    {
        return TerrainType.values()[Integer.parseInt(terrainTypeIndex)];
    }
}
