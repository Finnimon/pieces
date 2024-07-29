package com.gitgud.engine.model.gameobjects;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.graph.Graph;
import com.gitgud.graph.WeightedEdge;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;


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
    static ArrayList<Tile> getInAbsoluteRangeTiles(GridMap<?> gridMap, Tile position, int range)
    {
        List<Tile> tiles = gridMap.verticeSet()
                                  .stream()
                                  .filter(otherVertex -> position.distance(otherVertex) <= range)
                                  .toList();
        
        return new ArrayList<>(tiles.stream()
                                    .filter(tile -> tile.getTerrain().isTraversable())
                                    .filter(x -> !x.equals(position))
                                    .toList());
    }
    
    
    int getMovementRange();
    
    
    SimpleIntegerProperty movementRangeProperty();
    
    
    boolean isFlying();
    
    
    default boolean canMoveTo(GridMap<?> gridMap, Tile position, Tile target)
    {
        return findPossibleMovementTargets(gridMap, position).contains(target);
    }
    
    
    default ArrayList<Tile> findPossibleMovementTargets(GridMap<?> gridMap, Tile position)
    {
        ArrayList<Tile> result;
        if (isFlying())
        {
            result = getInAbsoluteRangeTiles(gridMap, position, getMovementRange());
            
            filterMovementTargetResult(result, position, gridMap);
        }
        else
        {
            result = getInNonFlyingRangeTiles(gridMap, position);
        }
        
        
        return result;
    }
    
    
    private void filterMovementTargetResult(ArrayList<Tile> result, Tile position, GridMap<?> gridMap)
    {
        ArrayList<Tile> inValidTiles = new ArrayList<>();
        Iterator<Tile> iterator = result.iterator();
        
        iterator.forEachRemaining(tile ->
                                  {
                                      if (tile == null)
                                      {
                                          inValidTiles.add(tile);
                                          return;
                                      }
                                      
                                      if (!tile.getTerrain().isTraversable())
                                      {
                                          inValidTiles.add(tile);
                                          return;
                                      }
                                      
                                      Object object = gridMap.get(tile);
                                      if (object != null)
                                      {
                                          inValidTiles.add(tile);
                                      }
                                  });
        result.removeAll(inValidTiles);
    }
    
    
    private ArrayList<Tile> getInNonFlyingRangeTiles(GridMap<?> gridMap, Tile position)
    {
        Predicate<Tile> filter = filter(gridMap);
        Graph<Tile, ?, WeightedEdge<Tile>> graph = gridMap.subGraph(position, getMovementRange(), filter, filter);
        TreeSet<Tile> tiles = graph.verticeSet();
        tiles.remove(position);
        
        return new ArrayList<>(tiles);
    }
    
    
    private Predicate<Tile> filter(GridMap<?> gridMap)
    {
        return tile -> gridMap.get(tile) == null;
    }
    
}
