package com.gitgud.graph;

public interface RectangularGraph<Vertex extends Vertex2D, Element>
{
    int getHeight();
    
    
    int getWidth();
    
    
    void updateDimensions();
    
    
    boolean isValid();
    
    
    boolean isVertexValid(Vertex vertex);
    
    
    default int calculateIndex(double x, double y)
    {
        return (int) Math.round(x + y * getWidth());
    }
    
    
    default int calculateIndex(float x, float y)
    {
        return calculateIndex((double) x, (double) y);
    }
    
    
    default int calculateIndex(int x, int y)
    {
        return calculateIndex((double) x, (double) y);
    }
    
    
    default int calculateIndex(Vertex vertex)
    {
        return calculateIndex(vertex.getX(), vertex.getY());
    }
    
    
    Vertex getVertex(double x, double y);
    
    
    Element get(double x, double y);
}
