package com.gitgud.model.map;


import com.gitgud.model.gameObjects.GridMappable;
import com.gitgud.model.graph.RectangularGraph;
import com.gitgud.model.graph.WeightedGraph;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


/**
 * A GridMap as graph
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 15.05.2024
 * @Version: 3.0
 */
public class GridMap<T extends GridMappable> extends AbstractMap<Tile, T> implements RectangularGraph<Tile, T>, WeightedGraph<Tile, T>, Serializable
{
    private final TreeMap<Tile, T> graph;
    
    
    private final int width;
    
    
    private final int height;
    
    
    /**
     * The maps adjecancyMatrix or edges
     *
     * @Author: Finn L.
     * @Owner: Finn L.
     */
    private final float[][] adjecancyMatrix;
    
    
    public GridMap(TreeMap<Tile, T> graph, int width, int height)
    {
        this.graph = graph;
        this.width = width;
        this.height = height;
        
        
        this.adjecancyMatrix = null;
    }
    
    
    public GridMap(TreeMap<Tile, T> graph, int width, int height, float[][] adjecancyMatrix)
    {
        this.graph = graph;
        this.width = width;
        this.height = height;
        this.adjecancyMatrix = adjecancyMatrix;
    }
    
    
    public float[][] getAdjecancyMatrix()
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
    
    
    public Tile getTile(int x, int y)
    {
        return getTileByIndex(calculateIndex(x, y));
    }
    
    
    public Tile getTile(T gridMappable)
    {
        return nodeSet().stream().filter(tile -> getElement(tile) == gridMappable).findFirst().orElse(null);
    }
    
    
    private boolean areTilesDirectlyConnected(Tile tile, Tile otherTile)
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
    
    
    /**
     * Returns all non-null T Values
     *
     * @return all non-null T Values in this map
     * @Author: Finn L.
     * @Owner: Finn L.
     * @Since: 15.05.2024
     * @Version: 1.0
     */
    public List<T> getAllGridMappables()
    {
        return this.graph.values().stream().filter(Objects::nonNull).toList();
    }
    
    
    public int getTileIndex(Tile tile)
    {
        return calculateIndex(tile.xPosition(), tile.yPosition());
    }
    
    
    public int calculateIndex(int x, int y)
    {
        return y * width + x;
    }
    
    
    public Tile getTileByIndex(int index)
    {
        return nodeSet().stream().filter(tile -> getTileIndex(tile) == index).findFirst().orElse(null);
    }
    
}
