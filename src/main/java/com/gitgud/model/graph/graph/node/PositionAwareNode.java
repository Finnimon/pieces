package com.gitgud.model.graph.graph.node;

public interface PositionAwareNode
{
    /**
     * must return false if other is null
     *
     * @param other
     * @return
     */
    public default boolean isAdjecant(PositionAwareNode other)
    {
        if (other == null || !(isTraversable() && other.isTraversable()))
        {
            return false;
        }
        return Math.abs(xPosition() - other.xPosition()) <= 1 && Math.abs(yPosition() - other.yPosition()) <= 1;
    }
    
    
    public int xPosition();
    
    
    public int yPosition();
    
    
    public boolean isTraversable();
}
