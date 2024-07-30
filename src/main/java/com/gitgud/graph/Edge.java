package com.gitgud.graph;

/**
 * <p>Represents an edge in a {@link Graph}.
 * <p>This edge is directed and therefore does not need a from vertex. That Information is stored in the containing
 * {@link Graph}.
 *
 * @param <Vertex> The type of {@link Vertex} the {@link Edge} is connected to
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.05.2024
 * @Version: 1.0
 */
public class Edge<Vertex>
{
    /**
     * The {@link Vertex} the {@link Edge} is connected to
     */
    private final Vertex to;
    
    
    /**
     * Standard Constructor for an {@link Edge}
     *
     * @param to The {@link Vertex} the {@link Edge} is connected to
     */
    public Edge(Vertex to)
    {
        this.to = to;
    }
    
    
    /**
     * Reversal of an {@link Edge}
     *
     * @param from the original origin vertex
     * @return a reversed {@link Edge} that is directed toward {@param from}
     */
    public Edge<Vertex> reverse(Vertex from)
    {
        return new Edge<>(from);
    }
    
    
    @Override
    public int hashCode()
    {
        return to.hashCode();
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Edge<?> other))
        {
            return false;
        }
        return to.equals(other.getTo());
    }
    
    
    /**
     * Getter for {@link #to}
     *
     * @return {@link #to}
     */
    public Vertex getTo()
    {
        return to;
    }
}
