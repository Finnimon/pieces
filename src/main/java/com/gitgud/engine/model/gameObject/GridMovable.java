package com.gitgud.engine.model.gameObject;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.graph.pathfinding.PathFinder;
import javafx.geometry.Point2D;

import java.util.Collection;
import java.util.List;


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
        if (isFlying())
        {
            return getInAbsoluteRangeTiles(gridMap, position);
        }
        
        return new PathFinder(gridMap).subGraph(position, getMovementRange()).verticeSet();
    }
    
    
    private List<Tile> getInAbsoluteRangeTiles(GridMap gridMap, Tile position)
    {
        return gridMap.verticeSet().stream().filter(
                otherVertex -> position.distance((Point2D) otherVertex) <= getMovementRange()).toList();
    }
    
}
