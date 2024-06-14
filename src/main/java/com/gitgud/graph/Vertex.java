package com.gitgud.graph;

public interface Vertex extends Comparable<Vertex>
{
    public int getIndex();
    
    public void setIndex(int index);
    
    
    @Override
    default int compareTo(Vertex o)
    {
        return Integer.compare(this.getIndex(), o.getIndex());
    }
}
