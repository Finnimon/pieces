package com.gitgud.graph;

import com.gitgud.engine.model.map.Tile;


/**
 * Implemented by {@link Graph}s that should be recangular
 *
 * @param <Vertex>
 * @param <Element>
 */
public interface RectangularGraph<Vertex extends Vertex2D, Element>
{
    int getHeight();
    
    
    int getWidth();
    
    
    void updateDimensions();
    
    
    Element place(int x, int y, Element element);
    
    
    boolean isValid();
    
    
    boolean isVertexValid(Vertex vertex);
    
    
    default int calculateIndex(double x, double y)
    {
        return (int) Math.round(x + y * getWidth());
    }
    
    
    default int calculateIndex(float x, float y)
    {
        return calculateIndex(x, (double) y);
    }
    
    
    default int calculateIndex(int x, int y)
    {
        return calculateIndex(x, (double) y);
    }
    
    
    default int calculateIndex(Vertex vertex)
    {
        return calculateIndex(vertex.getX(), vertex.getY());
    }
    
    
    Vertex getVertex(double x, double y);
    
    
    Element get(double x, double y);
    
    
    Tile[][] getVertexGrid();
}
