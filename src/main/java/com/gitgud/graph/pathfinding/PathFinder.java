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
public class PathFinder<Vertex extends com.gitgud.graph.Vertex, Element, Edge extends com.gitgud.graph.Edge<Vertex>> //todo specification of Element generic irrelevant
{
    private final Graph<Vertex, Element, Edge> graph;
    
    
    public PathFinder(Graph<Vertex, Element, Edge> graph)
    {
        this.graph = graph;
    }
    
    
    public Graph<Vertex, Element, Edge> getGraph()
    {
        return graph;
    }
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, double range)
    {
        return null;
    }
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, float range)
    {
        return subGraph(start, (double) range);
    }
    
    
    public Graph<Vertex, Element, Edge> subGraph(Vertex start, int range)
    {
        return subGraph(start, (double) range);
    }
    
    
    public Path<Vertex, Edge> shortestPath(Vertex from, Vertex to)
    {
        return null;
    }
    
    
}
