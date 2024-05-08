package com.gitgud.model.mission.map;


import com.gitgud.utility.Core;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A GridMap as graph
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class GridMap
{
    
    /**
     * The Maps Geographic Tiles or nodes
     *
     * @Author: Finn L.
     * @Owner: Finn L.
     */
    private final Tile[][] tiles;
    
    
    /**
     * The maps adjecancyMatrix or edges
     *
     * @Author: Finn L.
     * @Owner: Finn L.
     */
    private final Boolean[][] adjecancyMatrix;
    
    
    public GridMap(Tile[][] tiles) throws IllegalArgumentException
    {
        this.tiles = tiles;
        checkValidity();
        this.adjecancyMatrix = calulateAdjecancyMatrix();
        
    }
    
    
    private void checkValidity() throws IllegalArgumentException
    {
        Tile[][] tiles = getTiles();
        for (int y = 0; y < tiles.length; y++)
        {
            for (int x = 0; x < tiles[0].length; x++)
            {
                try
                {
                    tiles[y][x].validatePosition(x, y);
                }
                catch (ArrayIndexOutOfBoundsException | NullPointerException | MatchException e)
                {
                    throw new IllegalArgumentException("Tile at (" + x + "," + y + ")\t" + e.getMessage(), e);
                }
            }
        }
    }
    
    
    public Tile[][] getTiles()
    {
        return tiles;
    }
    
    
    public Boolean[][] getAdjecancyMatrix()
    {
        return adjecancyMatrix;
    }
    
    
    private Boolean[][] calulateAdjecancyMatrix()
    {
        Tile[][] tiles = getTiles();
        int tilesYLength = tiles.length;
        int tilesXLength = tiles[0].length;
        int connectionsLength = tilesYLength * tilesXLength;
        Boolean[][] connections = new Boolean[connectionsLength][connectionsLength];
        
        for (Tile tile : Arrays.stream(tiles).flatMap(Arrays::stream).toArray(Tile[]::new))
        {
            for (Tile neighbor : getNeighbors(tile))
            {
                boolean isConnected = isConnected(tile, neighbor);
                
                int tileAdjecancyIndex = tile.xPosition() + tilesXLength * tile.yPosition();
                int neighborAdjecancyIndex = tile.yPosition() + tilesYLength * tile.xPosition();
                
                Core.setMatrixValuesSymmetrically(connections, neighborAdjecancyIndex, tileAdjecancyIndex, isConnected);
            }
        }
        
        return connections;
    }
    
    
    public ArrayList<Tile> getNeighbors(Tile tile)
    {
        Tile[][] tiles = getTiles();
        ArrayList<Tile> neighbors = new ArrayList<>();
        int xPosition = tile.xPosition();
        int yPosition = tile.yPosition();
        
        
        for (int xShift = -1; xShift < 1; xShift++)
        {
            for (int yShift = -1; yShift < 1; yShift++)
            {
                try
                {
                    neighbors.add(tiles[yPosition][xPosition]);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    continue;
                }
            }
        }
        
        return neighbors;
    }
    
    
    private boolean isConnected(Tile tile, Tile otherTile)
    {
        if (Math.abs(tile.xPosition() - otherTile.xPosition()) > 1)
        {
            return false;
        }
        if (Math.abs(tile.yPosition() - otherTile.yPosition()) > 1)
        {
            return false;
        }
        
        
        return otherTile.terrain().isTraversable() && tile.terrain().isTraversable();
    }
    
}
