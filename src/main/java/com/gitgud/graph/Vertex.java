package com.gitgud.graph;


/**
 * A Vertex on a {@link Graph}
 *
 * @author Finn Lindig
 * @version 1.1
 * @Owner: Finn Lindig
 * @see Graph
 * @since 10.05.2024
 */
public interface Vertex extends Comparable<Vertex>
{
    
    /**
     * Getter for the index of this Vertex on its containing {@link Graph}
     *
     * @return the index of this Vertex on its containing {@link Graph}
     */
    int getIndex();
    
    
    /**
     * setter for the index of this Vertex on its containing {@link Graph}
     */
    void setIndex(int index);
    
    
    @Override
    default int compareTo(Vertex o)
    {
        return Integer.compare(this.getIndex(), o.getIndex());
    }
}
