package com.gitgud.model.graph.graph;


import com.gitgud.model.graph.graph.node.WeightedNode;


public interface WeightedGraph<Node extends WeightedNode &Comparable<Node>, Element> extends Graph<Node, Element>
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
