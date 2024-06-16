package com.gitgud.graph;

public interface RectangularGraph<Vertex extends com.gitgud.graph.Vertex>
{
    int getHeight();
    
    
    int getWidth();
    
    
    void updateDimensions();
    
    
    boolean isValid();
    
    
    boolean isVertexValid(Vertex vertex);
}
