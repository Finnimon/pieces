package com.gitgud.graph.pathfinding;

import com.gitgud.graph.WeightedEdge;

import java.util.List;


public class Path<Vertex extends com.gitgud.graph.Vertex, Edge extends com.gitgud.graph.Edge<Vertex>>
{
    
    //the path is unaware of its starting vertex because it only knows the edges after said start
    private final List<Edge> path;
    
    
    public Path(List<Edge> path)
    {
        this.path = path;
    }
    
    
    public List<Edge> getPath()
    {
        return path;
    }
    
    
    public float calculateLength()
    {
        float length = 0;
        
        for (Edge edge : path)
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
        path.add(edge);
        
        return calculateLength();
    }
    
    
}
