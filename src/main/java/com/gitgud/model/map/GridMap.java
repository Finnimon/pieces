package com.gitgud.model.map;


import com.gitgud.model.gameObjects.GridMappable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * A GridMap as graph
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class GridMap<T extends GridMappable>
{
    private final HashMap<Tile, T> graph;
    
    
    private final int width;
    
    
    private final int height;
    
    
    /**
     * The maps adjecancyMatrix or edges
     *
     * @Author: Finn L.
     * @Owner: Finn L.
     */
    private final Boolean[][] adjecancyMatrix;
    
    
    public GridMap(HashMap<Tile, T> graph, int width, int height)
    {
        this.graph = graph;
        this.width = width;
        this.height = height;
        
        
        this.adjecancyMatrix = null;
    }
    
    
    public HashMap<Tile, T> getGraph()
    {
        return this.graph;
    }
    
    
    public Boolean[][] getAdjecancyMatrix()
    {
        return adjecancyMatrix;
    }
    
    
    public int getWidth()
    {
        return width;
    }
    
    
    public int getHeight()
    {
        return height;
    }
    
    
    private boolean isConnected(Tile tile, Tile otherTile)
    {
        if (Math.abs(tile.xPosition() - otherTile.xPosition()) > 1)
        {
            return false;
        }
        if (Math.abs(tile.yPosition() - otherTile.yPosition()) > 1)
        {
            return false;
        }
        
        
        return otherTile.terrain().isTraversable() && tile.terrain().isTraversable();
    }
    
    public List<T> getAllGridMappables()
    {
        return this.graph.values().stream().filter(Objects::nonNull).toList();
    }
}
