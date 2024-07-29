package com.gitgud.graph;

/**
 * Point2D with absolute X and Y coordinates
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 1.0
 */
public interface Point2D
{
    /**
     * Getter for the absolute X coordinate
     * @return the absolute X coordinate
     */
    int getX();
    
    /**
     * Getter for the absolute Y coordinate
     * @return the absolute Y coordinate
     */
    int getY();
    
    
    /**
     * Calculates the length of a Vector between this and the given {@param other}
     * @param other the other point
     * @return the distance between this and the given {@param other}
     */
    default double distance(Point2D other)
    {
        return Math.sqrt(Math.pow(getX() - other.getX(), 2) + Math.pow(getY() - other.getY(), 2));
    }
}
