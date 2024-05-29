package com.gitgud.model.graph;

public interface WeightedGraph<Node extends Comparable<Node>, Element> extends Graph<Node, Element>
{
    public float[][] getWeightMatrix();
    
    
    public float[][] calculateWeightMatrix();
    
    
    public default float getWeight(Node from, Node to)
    {
        return getWeightMatrix()[getNodeIndex(from)][getNodeIndex(to)];
    }
    
    
    public default float getWeight(Node from, Element to)
    {
        return getWeight(from, getNode(to));
    }
    
    
    public default float getWeight(Element from, Element to)
    {
        return getWeight(getNode(from), getNode(to));
    }
    
}
