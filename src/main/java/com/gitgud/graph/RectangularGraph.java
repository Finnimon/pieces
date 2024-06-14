package com.gitgud.graph;

public interface RectangularGraph<Vertex extends com.gitgud.graph.Vertex>
{
    public int getHeight();
    
    
    public int getWidth();
    
    
    public void updateDimensions();
    
    
    public boolean isValid();
    
    public boolean isVertexValid(Vertex vertex);
}
