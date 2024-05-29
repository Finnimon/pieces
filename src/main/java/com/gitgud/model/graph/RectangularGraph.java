package com.gitgud.model.graph;

public interface RectangularGraph<Node extends Comparable<Node>, Element> extends Graph<Node, Element>
{
    public int getWidth();
    
    
    public int getHeight();
    
    
    public boolean isRectangular();
    
    
    private void validateRectangularGraph()
    {
        if (!isRectangular())
        {
            throw new IllegalStateException("Graph is not rectangular");
        }
    }
}
