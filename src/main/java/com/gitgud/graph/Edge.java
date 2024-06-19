package com.gitgud.graph;

public class Edge<Vertex>
{
    private final Vertex to;
    
    
    public Edge(Vertex to)
    {
        this.to = to;
    }
    
    
    public Vertex getTo()
    {
        return to;
    }
    
    
    public Edge<Vertex> reverse(Vertex from)
    {
        return new Edge<>(from);
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Edge)
        {
            return to.equals(((Edge<?>) obj).getTo());
        }
        return super.equals(obj);
    }
    
    
    @Override
    public int hashCode()
    {
        return to.hashCode();
    }
}
