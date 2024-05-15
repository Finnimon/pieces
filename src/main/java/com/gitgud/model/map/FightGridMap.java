package com.gitgud.model.map;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;

import java.util.HashMap;


//
public class FightGridMap extends GridMap<FightAgent>
{
    private static final int EDGE_LENGTH = 12;
    
    
    public FightGridMap(HashMap<Tile, FightAgent> graph, int width, int height)
    {
        super(graph, width, height);
        if (width != EDGE_LENGTH || height != EDGE_LENGTH)
        {
            throw new IllegalArgumentException("FightGridMap Argument has wrong dimensions");
        }
    }
    
    
}
