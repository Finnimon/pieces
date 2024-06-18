package com.gitgud.graph.pathfinding;

import com.gitgud.graph.Graph;


/**
 * Finds paths and distances between {@link com.gitgud.graph.Graph} vertices.
 * Assumes edge length 1 for non-Weighted edges.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 2.0
 */
public class PathFinder<Vertex extends com.gitgud.graph.Vertex, Element, Edge extends com.gitgud.graph.Edge<Vertex>,GraphType extends Graph<Vertex, Element, Edge>> //todo specification of Element generic irrelevant
{
    private final GraphType graph;
    
    
    public PathFinder(GraphType graph)
    {
        this.graph = graph;
    }
    
    
    public GraphType getGraph()
    {
        return graph;
    }
    
    
    
    
    
    public Path<Vertex, Edge> shortestPath(Vertex from, Vertex to)
    {
        return null;
    }
    
    
}
