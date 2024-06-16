package com.gitgud.graph.pathfinding;

import com.gitgud.graph.WeightedEdge;

import java.util.LinkedList;
import java.util.List;


public class Path<Vertex extends com.gitgud.graph.Vertex, Edge extends com.gitgud.graph.Edge<Vertex>> extends LinkedList<Edge>
{
    
    
    public float calculateLength()
    {
        float length = 0;
        
        for (Edge edge : this)
        {
            length += determineEdgeLength(edge);
        }
        
        
        return length;
    }
    
    
    public float determineEdgeLength(Edge edge)
    {
        if (!(edge instanceof WeightedEdge<?>))
        {
            return 1;
        }
        
        
        return ((WeightedEdge<?>) edge).getWeight();
    }
    
    
    public float addEdge(Edge edge)
    {
        add(edge);
        
        return calculateLength();
    }
    
    
}
