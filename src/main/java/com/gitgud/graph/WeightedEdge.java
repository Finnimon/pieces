package com.gitgud.graph;

public class WeightedEdge<Vertex> extends Edge<Vertex>
{
    private final float weight;
    
    
    public float getWeight()
    {
        return weight;
    }
    
    
    public WeightedEdge(Vertex end, float weight)
    {
        super(end);
        this.weight = weight;
    }
    
    
    public WeightedEdge(Vertex vertex)
    {
        super(vertex);
        weight = 1;
    }
    
    
    @Override
    public WeightedEdge<Vertex> reverse(Vertex from)
    {
        return new WeightedEdge<>(from, weight);
    }
}
