package com.gitgud.graph;

/**
 * A Weighted edge
 *
 * @param <Vertex> The Type of {@link com.gitgud.graph.Vertex} connected by this Edge
 * @author Finn Lindig
 * @version 1.1
 * @Owner: Finn Lindig
 * @see WeightedGraph
 * @since 10.05.2024
 */
public class WeightedEdge<Vertex> extends Edge<Vertex>
{
    
    
    private static final int DEFAULT_WEIGHT = 1;
    
    
    /**
     * The weight or length of this edge
     */
    private final double weight;
    
    
    /**
     * Constructs the Edge and assigns {@link Point2D#distance(Point2D)} as {@link #weight}
     *
     * @param from starting point of the edge
     * @param to   endpoint of the Edge
     * @Precondition: {@link Vertex} extends {@link Point2D}
     * @Postcondition: No exceptions will be thrown. The {@link WeightedEdge} will be correctly instantiated.
     */
    public WeightedEdge(Point2D from, Point2D to)
    {
        this((Vertex) to, from.distance(to));
    }
    
    
    /**
     * Default Constructor for {@link WeightedEdge}
     *
     * @param to     the EndPoint or {@link Edge#getTo()}
     * @param weight the weight for this {@link WeightedEdge}
     */
    public WeightedEdge(Vertex to, double weight)
    {
        super(to);
        this.weight = weight;
    }
    
    
    /**
     * Constructor with {@link #DEFAULT_WEIGHT}
     *
     * @param to the endpoint of this edge
     */
    public WeightedEdge(Vertex to)
    {
        super(to);
        weight = DEFAULT_WEIGHT;
    }
    
    
    /**
     * Getter for {@link #weight}
     *
     * @return the {@link #weight} of this edge
     */
    public double getWeight()
    {
        return weight;
    }
    
    
    @Override
    public WeightedEdge<Vertex> reverse(Vertex from)
    {
        return new WeightedEdge<>(from, weight);
    }
}
