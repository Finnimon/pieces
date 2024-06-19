package com.gitgud.graph;

import javafx.geometry.Point2D;


public abstract class Vertex2D extends Point2D implements Vertex
{
    /**
     * Creates a new instance of {@code Point2D}.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Vertex2D(double x, double y)
    {
        super(x, y);
    }
}
