package com.gitgud.engine.model.gameObject;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.utility.services.GridMapServices;

import java.util.Collection;
import java.util.stream.Stream;


/**
 * Objects with a range of movement and a type of movement on a {@link GridMap}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 2.0
 * @see com.gitgud.engine.model.gameObject.agent.Agent
 */
public interface GridMovable extends GridMappable
{
    int getMovementRange();
    
    
    boolean isFlying();
    
    
    default boolean canMoveTo(GridMap gridMap, Tile position, Tile target)
    {
        return getInRangeTiles(gridMap, position).contains(target);
    }
    
    
    default Collection<Tile> getInRangeTiles(GridMap gridMap, Tile position)
    {
        int movementRange = getMovementRange();
        if (!isFlying())
        {
            return GridMapServices.getInRange(gridMap, position, movementRange);
        }
        
        
        Stream<Tile> reachableTiles = gridMap.keySet().stream();
        
        return reachableTiles.filter(tile -> tile.terrain().isTraversable()).filter(
                tile -> movementRange >= calculateAbsoluteDistance(position, tile)).toList();
    }
    
    
    private static double calculateAbsoluteDistance(Tile a, Tile b)
    {
        return Math.sqrt(Math.pow(a.xPosition() - b.yPosition(), 2) + Math.pow(a.yPosition() - b.yPosition(), 2));
    }
    
}
