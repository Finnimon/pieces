package com.gitgud.model.graph.node;

public interface Node extends Comparable<Node>
{
    /**
     * must return false if other is null
     * @param other
     * @return
     */
    public boolean isAdjecant(Node other);
    
}
