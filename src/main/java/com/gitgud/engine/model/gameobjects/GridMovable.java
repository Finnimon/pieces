package com.gitgud.engine.model.gameobjects;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.graph.Graph;
import com.gitgud.graph.Point2D;
import com.gitgud.graph.Vertex;
import com.gitgud.graph.WeightedEdge;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
    /**
     * Determines all Tiles on {@code gridMap} that are in range of {@code position}, going by
     * {@link com.gitgud.graph.Point2D#distance(Point2D)}
     *
     * @param gridMap  The GridMap to traverse.
     * @param position The root of the traversal.
     * @param range    The range of the traversal.
     * @return All Tiles in range of {@code position} with {@link #getMovementRange()} as range.
     */
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
    
    
    /**
     * Finds and returns possible movement targets.
     *
     * @param gridMap  The GridMap that this {@link GridMovable} is on.
     * @param position The position of this {@link GridMovable}.
     * @return All movement targets available to this {@link GridMovable} excluding {@code position}.
     */
    default ArrayList<Tile> findPossibleMovementTargets(GridMap<?> gridMap, Tile position)
    {
        if (!isFlying())
        {
            return getInNonFlyingRangeTiles(gridMap, position);
        }
        
        ArrayList<Tile> result = getInAbsoluteRangeTiles(gridMap, position, getMovementRange());
        filterMovementTargetResult(result, position, gridMap);
        return result;
    }
    
    
    /**
     * <p>Getter for whether this {@link GridMovable} can fly.
     * <p>Flying {@link GridMovable}s can move over {@link Tile}s that are not traversable and ignore Edges.
     *
     * @return Whether this {@link GridMovable} can fly.
     */
    boolean isFlying();
    
    
    /**
     * Determines all Tiles on {@code gridMap} that are in range of {@code position}, going by the GridMaps
     * {@link Graph#getEdges(Vertex)}.
     *
     * @param gridMap  The GridMap to traverse.
     * @param position The root of the traversal.
     * @return All Tiles in range of {@code position} with {@link #getMovementRange()} as range.
     * @see GridMap#subGraph(Vertex, int, Predicate, Predicate)
     */
    private ArrayList<Tile> getInNonFlyingRangeTiles(GridMap<?> gridMap, Tile position)
    {
        Predicate<Tile> filter = filter(gridMap);
        Graph<Tile, ?, WeightedEdge<Tile>> graph = gridMap.subGraph(position, getMovementRange(), filter, filter);
        Set<Tile> tiles = graph.verticeSet();
        tiles.remove(position);
        
        return new ArrayList<>(tiles);
    }
    
    
    /**
     * Getter for the movement range.
     *
     * @return The movement range.
     */
    default int getMovementRange()
    {
        return movementRangeProperty().get();
    }
    
    
    /**
     * Setter for the movement range.
     *
     * @param movementRange The new movement range.
     */
    default void setMovementRange(int movementRange)
    {
        movementRangeProperty().setValue(movementRange);
    }
    
    
    /**
     * Getter for the movement range property.
     *
     * @return The movement range property
     */
    SimpleIntegerProperty movementRangeProperty();
    
    
    /**
     * Removes invalid movement targets from {@code result}.
     *
     * @param result   The List of movement targets to filter.
     * @param position The position of this {@link GridMovable}.
     * @param gridMap  The GridMap that this {@link GridMovable} is on.
     */
    private void filterMovementTargetResult(ArrayList<Tile> result, Tile position, GridMap<?> gridMap)
    {
        ArrayList<Tile> inValidTiles = new ArrayList<>();
        inValidTiles.add(position);
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
    
    
    /**
     * Creates and returns a Predicate that filters out {@link Tile}s that are occupied by {@link GridMappable}s.
     * @param gridMap The GridMap that this {@link GridMovable} is on.
     * @return The Predicate.
     */
    private static Predicate<Tile> filter(GridMap<?> gridMap)
    {
        return tile -> gridMap.get(tile) == null;
    }
    
}
