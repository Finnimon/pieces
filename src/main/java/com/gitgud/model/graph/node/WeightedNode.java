package com.gitgud.model.graph.node;

import static java.lang.Float.NaN;


public interface WeightedNode extends Node
{
    public static float DirectWeight(WeightedNode from, WeightedNode to)
    {
        return from.getDirectWeightTo(to);
    }
    
    
    public static float calculateDirectPathWeight(WeightedNode from, WeightedNode to)
    {
        return from.calculateDirectPathWeight(to);
    }
    
    
    public default float getDirectWeightTo(WeightedNode other)
    {
        if (!isAdjecant(other))
        {
            return NaN;
        }
        
        
        return calculateDirectPathWeight(other);
    }
    
    
    public float calculateDirectPathWeight(WeightedNode other);
    
}
