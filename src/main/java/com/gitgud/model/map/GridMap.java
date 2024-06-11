package com.gitgud.model.map;


import com.gitgud.model.gameObjects.GridMappable;
import com.gitgud.model.graph.graph.RectangularGraph;
import com.gitgud.model.graph.graph.WeightedGraph;

import java.util.TreeMap;


public class GridMap<T extends GridMappable> implements RectangularGraph<Tile, T>, WeightedGraph<Tile, T>
{
    private final TreeMap<Tile, T> map;
    
    
    private final int width;
    
    
    private final int height;
    
    
    private final boolean[][] adjecancyMatric;
    
    
    private final float[][] weightMatrix;
    
    
    public GridMap(TreeMap<Tile, T> map, int width, int height, boolean[][] adjecancyMatric, float[][] weightMatrix)
    {
        this.map = map;
        this.width = width;
        this.height = height;
        this.adjecancyMatric = adjecancyMatric;
        this.weightMatrix = weightMatrix;
    }
    
    
    @Override
    public TreeMap<Tile, T> getMap()
    {
        if (StackWalker.getInstance().getCallerClass() != GridMap.class)
        {
            throw new IllegalCallerException();
        }
        return map;
    }
    
    
    @Override
    public boolean[][] getAdjecancyMatrix()
    {
        return adjecancyMatric;
    }
    
    
    @Override
    public boolean[][] calculateAdjecancyMatrix()
    {
        return null; //todo!!
    }
    
    
    @Override
    public int getWidth()
    {
        return width;
    }
    
    
    @Override
    public int getHeight()
    {
        return height;
    }
    
    
    @Override
    public boolean isRectangular()
    {
        return true;//todo
    }
    
    
    @Override
    public float[][] getWeightMatrix()
    {
        return this.weightMatrix;
    }
    
    
    @Override
    public float[][] calculateWeightMatrix()
    {
        return new float[0][];
    }
}
