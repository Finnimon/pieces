package com.gitgud.graph;

public interface Point2D
{
    int getX();
    int getY();
    
    default double distance(Point2D other)
    {
        return Math.sqrt(Math.pow(getX() - other.getX(), 2) + Math.pow(getY() - other.getY(), 2));
    }
}
