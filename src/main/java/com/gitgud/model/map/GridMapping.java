package com.gitgud.model.map;

import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.gameObjects.GridMappable;

import java.util.Map;


/**
 * Maps a {@link GameObject} to a {@link Tile}
 * <p>
 * used by {@link GridMapContext}
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @see GridMapContext
 * @see GameObject
 * @since 24.04.2024
 */
public class GridMapping<T extends GridMappable> implements Map.Entry<GridMappable, Tile>
{
    private final T gridMappable;
    
    
    private volatile Tile tile;
    
    
    public GridMapping(T gridMappable, Tile tile)
    {
        this.gridMappable = gridMappable;
        this.tile = tile;
    }
    
    
    @Override
    public T getKey()
    {
        return gridMappable;
    }
    
    
    @Override
    public Tile getValue()
    {
        return tile;
    }
    
    
    @Override
    public Tile setValue(Tile value)
    {
        Tile oldTile = tile;
        tile = value;
        return oldTile;
    }
    
    
    @Override
    public boolean equals(Object other)
    {
        GridMapping<T> otherGridMapping = (GridMapping<T>) other;
        
        return gridMappable.equals(otherGridMapping.gridMappable) && tile.equals(otherGridMapping.tile);
    }
    
    
    @Override
    public int hashCode()
    {
        return getKey().hashCode();
    }
}
