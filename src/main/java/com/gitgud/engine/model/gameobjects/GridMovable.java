package com.gitgud.engine.model.gameobjects;

import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.graph.Graph;
import com.gitgud.graph.WeightedEdge;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;


/**
 * Objects with a range of movement and a type of movement on a {@link GridMap}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 2.0
 * @see com.gitgud.engine.model.gameobjects.agent.Agent
 */
public interface GridMovable extends GridMappable
{
    int getMovementRange();
    
    
    boolean isFlying();
    
    
    default boolean canMoveTo(GridMap<?> gridMap, Tile position, Tile target)
    {
        return findPosibleMovementTargets(gridMap, position).contains(target);
    }
    
    
    default Collection<Tile> findPosibleMovementTargets(GridMap<?> gridMap, Tile position)
    {
        Collection<Tile> result;
        if (isFlying())
        {
            result = getInAbsoluteRangeTiles(gridMap, position);
        }
        else
        {
            result = getInNonFlyingRangeTiles(gridMap, position);
        }
        
        
        return result;
    }
    
    
    private void filterMovementTargetResult(Collection<Tile> result, Tile position, GridMap<?> gridMap)
    {
        HashSet<Tile> inValids = new HashSet<>();
        inValids.add(position);
        
        for (Tile tile : result)
        {
            if (tile.getTerrain().isTraversable())
            {
                inValids.add(tile);
                continue;
            }
            
            if (gridMap.get(tile) == null || gridMap.get(tile) instanceof Interactable)
            {
                continue;
            }
            
            inValids.add(tile);
        }
        
        result.removeAll(inValids);
    }
    
    
    private Collection<Tile> getInNonFlyingRangeTiles(GridMap<?> gridMap, Tile position)
    {
        Graph<Tile, ?, WeightedEdge<Tile>> graph = gridMap.subGraph(position, getMovementRange());
        TreeSet<Tile> tiles = graph.verticeSet();
        tiles.remove(position);
        
        return tiles;
    }
    
    
    private Collection<Tile> getInAbsoluteRangeTiles(GridMap<?> gridMap, Tile position)
    {
        List<Tile> tiles = gridMap.verticeSet().stream().filter(
                otherVertex -> position.distance(otherVertex) <= getMovementRange()).toList();
        
        return tiles.stream().filter(tile -> tile.getTerrain().isTraversable()).filter(
                x -> !x.equals(position)).toList();
    }
    
}
