package com.gitgud.model.map;


import com.gitgud.model.graph.graph.node.WeightedNode;


public class Tile implements Comparable<Tile>, WeightedNode
{
    private final int xPosition;
    
    
    private final int yPosition;
    
    
    private final Terrain terrain;
    
    public Tile(int xPosition, int yPosition, Terrain terrain)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.terrain = terrain;
    }
    
    
    @Override
    public int xPosition()
    {
        return this.xPosition;
    }
    
    
    @Override
    public int yPosition()
    {
        return this.yPosition;
    }
    
    
    @Override
    public boolean isTraversable()
    {
        return false;
    }
    
    
    @Override
    public float calculateDirectPathWeight(WeightedNode other)
    {
        return (float) Math.sqrt(
                Math.pow(xPosition() - other.xPosition(), 2) + Math.pow(yPosition() - other.yPosition(), 2));
    }
    
    
    @Override
    public int compareTo(Tile o)
    {
        int result = Integer.compare(this.yPosition, o.yPosition);
        if (result == 0)
        {
            result = Integer.compare(this.xPosition, o.xPosition);
        }
        
        return result;
    }
    
    
    public Terrain getTerrain()
    {
        return terrain;
    }
}
