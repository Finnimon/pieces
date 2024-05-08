package com.gitgud.model.mission.map;

//
public class FightGridMap extends GridMap
{
    private static final int EDGE_LENGTH = 12;
    
    
    public FightGridMap(Tile[][] tiles)
    {
        super(tiles);
        
        checkValidity(tiles);
    }
    
    
    private static void validateDimensions(Tile[][] tiles)
    {
        if (tiles.length != EDGE_LENGTH || tiles[0].length != EDGE_LENGTH)
        {
            throw new IllegalArgumentException("FightGridMap Argument has wrong dimensions");
        }
    }
    
    
    private void checkValidity(Tile[][] tiles)
    {
        validateDimensions(tiles);
    }
}
