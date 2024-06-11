package com.gitgud.model.graph.graph;


import com.gitgud.model.graph.graph.node.PositionAwareNode;


public interface RectangularGraph<T extends PositionAwareNode & Comparable<T>, Element> extends Graph<T, Element>
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
    
    
    @Override
    public default int getNodeIndex(T t)
    {
        return calculateIndex(t.xPosition(), t.yPosition());
    }
    

    
    
    public default int calculateIndex(int x, int y)
    {
        return y * getWidth() + x;
    }
    
}
