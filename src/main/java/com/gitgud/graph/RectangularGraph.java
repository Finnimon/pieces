package com.gitgud.graph;

/**
 * <p>Implemented by {@link Graph}s that should be recangular.
 * <p>This interface allows different operations on the {@link Graph} by assuming it is a Grid of vertices.
 *
 * @param <Vertex>  The Type of Vertex on this Graph
 * @param <Element> The Type of Element on this Graph
 * @author Finn Lindig
 * @version 1.0
 * @Owner: Finn Lindig
 * @since 12.05.2024
 */
public interface RectangularGraph<Vertex extends Vertex2D, Element>
{
    /**
     * <p>Getter for height of the {@link RectangularGraph} assuming that it is rectangular and every
     * {@link Vertex2D} has the same height of 1.
     * <p>In other words the number of {@link Vertex2D} in the {@link Graph} is equal to {@link #getWidth()} *
     * {@link #getHeight()}
     *
     * @return the height
     */
    int getHeight();
    
    
    /**
     * Recalculates the {@link #getWidth()} and {@link #getHeight()} and sets them.
     */
    void updateDimensions();
    
    
    /**
     * Places on the grid at {@param x} and {@param y} the {@param element}
     *
     * @param x       the x coordinate of the {@link Vertex2D} to place {@param element}
     * @param y       the y coordinate of the {@link Vertex2D} to place {@param element}
     * @param element the {@link Element} to place
     * @return the previous Element or null if there was none
     */
    Element place(int x, int y, Element element);
    
    
    /**
     * Checks all Vertices in {@link Graph#verticeSet()} for validity
     *
     * @return if all Vertices in this Graph are valid
     */
    default boolean isValid()
    {
        for (Vertex vertex : ((Graph<Vertex, ?, ?>) this).verticeSet())
        {
            if (!isVertexValid(vertex))
            {
                return false;
            }
        }
        
        
        return true;
    }
    
    
    /**
     * <p>Checks if {@param vertex} is valid going by its {@link Vertex2D#getX()}, {@link Vertex2D#getY()} and
     * {@link Vertex2D#getIndex()}
     * <p>{@link Vertex2D#getX()}, {@link Vertex2D#getY()} and {@link Vertex2D#getIndex()}
     *
     * @param vertex the {@link Vertex2D} to be checked for validity
     * @return if the {@param vertex} is valid
     */
    default boolean isVertexValid(Vertex vertex)
    {
        int index = vertex.getIndex();
        
        boolean isValid = ((Graph<?, ?, ?>) this).getVertex(index) == vertex;
        
        if (!isValid)
        {
            return false;
        }
        
        isValid = calculateIndex(vertex) == index;
        
        return isValid;
    }
    
    
    /**
     * Calculates the proper index for {@param vertex} and returns it
     *
     * @param vertex the vertex to calculate an Index for
     * @return the proper index for {@param vertex}
     * @see #calculateIndex(int, int)
     */
    default int calculateIndex(Vertex vertex)
    {
        return calculateIndex(vertex.getX(), vertex.getY());
    }
    
    
    /**
     * Calculates the proper Index for a {@link Vertex} at the {@link Point2D} ({@param x},{@param y})
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return the proper Index for a {@link Vertex} at the {@link Point2D} ({@param x},{@param y})
     */
    default int calculateIndex(int x, int y)
    {
        return x + y * getWidth();
    }
    
    
    /**
     * <p>Getter for width of the {@link RectangularGraph} assuming that it is rectangular and every {@link Vertex2D}
     * has the same width of 1.
     * <p>In other words the number of {@link Vertex2D} in the {@link Graph} is equal to {@link #getWidth()} *
     * {@link #getHeight()}
     *
     * @return the width
     */
    int getWidth();
    
    
    /**
     * Finds the Vertex at the Point ({@param x},{@param y})
     *
     * @param x the x coordinate of the queried {@link Vertex}
     * @param y the Y coordinate of the queried {@link Vertex}
     * @return The Vertex at the Point ({@param x},{@param y})
     */
    default Vertex getVertex(int x, int y)
    {
        return ((Graph<Vertex, Element, ?>) this).getVertex(calculateIndex(x, y));
    }
    
    
    /**
     * Finds the {@link Element} at the point ({@param x},{@param y}) on this {@link Graph} or null
     *
     * @param x the x coordinate to query
     * @param y the y coordinate to query
     * @return the {@link Element} at the point ({@param x},{@param y}) on this {@link Graph}
     */
    default Element get(int x, int y)
    {
        return ((Graph<Vertex, Element, ?>) this).get(calculateIndex(x, y));
    }
    
    
    /**
     * Gets the Vertices in the Grid as defined by {@link Vertex#getX()} {@link Vertex#getY()}
     *
     * @return The Vertices of this Graph as a 2D Array/Grid
     */
    Vertex[][] getVertexGrid();
}
