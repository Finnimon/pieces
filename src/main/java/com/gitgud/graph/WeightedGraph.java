package com.gitgud.graph;

import java.util.HashSet;
import java.util.TreeMap;


public class WeightedGraph<Vertex extends com.gitgud.graph.Vertex, Element> extends Graph<Vertex, Element, WeightedEdge<Vertex>>
{
    public WeightedGraph(TreeMap<Vertex, Element> vertices, TreeMap<Vertex, HashSet<WeightedEdge<Vertex>>> edges)
    {
        super(vertices, edges);
    }
    
    
    public WeightedGraph()
    {
    }
}
