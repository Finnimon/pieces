package com.gitgud.graph;

import java.util.HashSet;
import java.util.TreeMap;


/**
 * A {@link Graph} with {@link WeightedEdge}s as {@link Edge}s
 *
 * @param <Vertex>
 * @param <Element>
 * @author Finn Lindig
 * @version 1.0
 * @Owner: Finn Lindig
 * @see WeightedEdge
 * @since 10.05.2024
 */
public class WeightedGraph<Vertex extends com.gitgud.graph.Vertex, Element> extends Graph<Vertex, Element, WeightedEdge<Vertex>>
{
    /**
     * default Constructor
     *
     * @param vertices The Vertice map for this Graph
     * @param edges    The Edges for this Graph
     * @Precondition: All Vertices in {@param edges} are in {@param vertices} also and the other way around and their indexing is intact.
     * @Postcondition: The Weighted Graph will function properly
     */
    public WeightedGraph(TreeMap<Vertex, Element> vertices, TreeMap<Vertex, HashSet<WeightedEdge<Vertex>>> edges)
    {
        super(vertices, edges);
    }
    
    
    /**
     * Default Constructor that creates and Empty Weighted Graph
     */
    public WeightedGraph()
    {
    }
}
