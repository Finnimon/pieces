package com.gitgud.engine.model.map;

import com.gitgud.graph.RectangularGraph;
import com.gitgud.graph.WeightedEdge;
import com.gitgud.graph.WeightedGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;


/**
 * The default Graph for 2D Maps. To ensure proper funcionality maintain rectangularity. it's Vertex {@link Tile}
 * extend {@link javafx.geometry.Point2D}.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 2.0
 */
public class GridMap<GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable>
        extends WeightedGraph<Tile, GridMappableType> implements RectangularGraph<Tile, GridMappableType>
{
    //    private final float tileSpacing;
    
    
    /**
     * The Width of the {@link GridMap}.
     */
    private int width;
    
    
    /**
     * The Height of the {@link GridMap}.
     */
    private int height;
    
    
    /**
     * Initializes the {@link GridMap} with the given {@link TreeMap} vertices and {@link TreeMap} edges.
     *
     * @param vertices The initial {@link TreeMap} of {@link Tile}s.
     * @param edges    The initial {@link TreeMap} of {@link WeightedEdge}s.
     * @Precondition: The {@code vertices} and {@code edges} should be intact.
     * @Postcondition: The GridMap will function properly.
     */
    public GridMap(TreeMap<Tile, GridMappableType> vertices, TreeMap<Tile, HashSet<WeightedEdge<Tile>>> edges)
    {
        super(vertices, edges);
        updateDimensions();
    }
    
    
    /**
     * Initializes the {@link GridMap} as empty.
     */
    public GridMap()
    {
        updateDimensions();
    }
    
    
    /**
     * Factory method to create a {@link GridMap} from a 2D boolean array. This method is for dev purposes only and
     * should be removed from Production code.
     *
     * @param grid               The 2D boolean array that represent the {@link GridMap}. true = traversable, false =
     *                           non-traversable.
     * @param <GridMappableType> The Type of the {@link com.gitgud.engine.model.gameobjects.GridMappable} on this
     *                           GridMap.
     * @return A GridMap with the given 2D boolean array and concluded Edges.
     * @Precondition: The param {@code grid} should rectangular.
     * @Postcondition: The GridMap will function properly.
     */
    public static <GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable> GridMap<GridMappableType> create(
            boolean[][] grid)
    {
        int height = grid.length;
        int width = grid[0].length;
        
        TerrainType[][] terrainGrid = new TerrainType[height][width];
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                terrainGrid[y][x] = grid[y][x] ? TerrainType.TRAVERSABLE : TerrainType.NON_TRAVERSABLE;
            }
        }
        
        return create(terrainGrid);
    }
    
    
    /**
     * Factory method to create a {@link GridMap} from a 2D TerrainType array. This method is for dev purposes only
     * and should be removed from Production code.
     *
     * @param grid               The 2D TerrainType array that represents the {@link GridMap}s terrain.
     * @param <GridMappableType> The Type of the {@link com.gitgud.engine.model.gameobjects.GridMappable} on this
     *                           GridMap.
     * @return A GridMap with the given Terrain and concluded Edges.
     * @Precondition: The param {@code grid} should rectangular.
     * @Postcondition: The GridMap will function properly.
     */
    public static <GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable> GridMap<GridMappableType> create(
            TerrainType[][] grid)
    {
        GridMap<GridMappableType> gridMap = new GridMap<>();
        Tile[][] tileGrid = tileGridFromTerrainTypeGrid(grid);
        gridMap.height = tileGrid.length;
        gridMap.width = tileGrid[0].length;
        for (int y = 0; y < gridMap.height; y++)
        {
            for (int x = 0; x < gridMap.width; x++)
            {
                gridMap.getVertices().put(tileGrid[y][x], null);
            }
        }
        gridMap.drawConcludableEdges();
        
        
        return gridMap;
    }
    
    
    /**
     * Helper method for {@link #create(TerrainType[][])}.  This method is for dev purposes only and should be
     * removed from Production code.
     *
     * @param grid The 2D TerrainType array that represents the {@link GridMap}s terrain.
     * @return A 2D Tile array from the given 2D TerrainType array.
     */
    private static Tile[][] tileGridFromTerrainTypeGrid(TerrainType[][] grid)
    {
        int height = grid.length;
        int width = grid[0].length;
        Tile[][] tileGrid = new Tile[height][width];
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                tileGrid[y][x] = new Tile(x, y, grid[y][x], width);
                
            }
        }
        return tileGrid;
    }
    
    
    /**
     * Draws all edges that should exist on the {@link GridMap}.
     */
    private void drawConcludableEdges()
    {
        for (Tile tile : getVertices().keySet())
        {
            if (!tile.getTerrain().isTraversable())
            {
                getEdgeMap().put(tile, new HashSet<>());
                continue;
            }
            
            connectNeighbors(tile);
        }
    }
    
    
    /**
     * Connects the given {@link Tile} to its traversable neighbors.
     *
     * @param tile The Tile to connect.
     */
    private void connectNeighbors(Tile tile)
    {
        
        Collection<Tile> neighbors = getNeighbors(tile);
        Collection<Tile> traversableNeighbors = neighbors.stream().filter(n -> n.getTerrain().isTraversable()).toList();
        
        for (Tile neighbor : traversableNeighbors)
        {
            double distance = tile.distance(neighbor);
            
            if (!checkShouldEdgeBeAdded(tile, neighbor))
            {
                continue;
            }
            
            addEdge(tile, new WeightedEdge<>(neighbor, (float) distance));
        }
    }
    
    
    /**
     * Get the neighbors of the given {@link Tile}.
     *
     * @param tile The Tile to get the neighbors of.
     * @return All tiles that are within a distance of sqrt(2) of the given Tile.
     */
    public Collection<Tile> getNeighbors(Tile tile)
    {
        HashSet<Tile> neighbors = new HashSet<>();
        float adjuster = 0.01f;
        
        for (Tile neighbor : getVertices().keySet())
        {
            if (!(tile.distance(neighbor) < Math.sqrt(2) + adjuster))
            {
                continue;
            }
            if (tile == neighbor)
            {
                continue;
            }
            
            neighbors.add(neighbor);
        }
        return neighbors;
    }
    
    
    /**
     * Example:
     * 1O0
     * O10
     * 001
     * the traversable 1s are not seen as connected as the non-Traversable 0s are also converging at the same point
     *
     * @param tile      The Tile to check.
     * @param otherTile The other Tile to check.
     * @return If an edge Should exist between the two given Tiles. Assuming the Tiles are neighbors and Traversable.
     */
    private boolean checkShouldEdgeBeAdded(Tile tile, Tile otherTile)
    {
        int x = tile.getX();
        int y = tile.getY();
        int otherX = otherTile.getX();
        int otherY = otherTile.getY();
        return getVertex(otherX, y).getTerrain().isTraversable() || getVertex(x, otherY).getTerrain().isTraversable();
    }
    
    
    @Override
    public int getHeight()
    {
        return height;
    }
    
    
    @Override
    public void updateDimensions()
    {
        TreeMap<Tile, GridMappableType> vertices = this.getVertices();
        if (vertices.isEmpty())
        {
            width = 0;
            height = 0;
            return;
        }
        width = Math.round(vertices.lastKey().getX() + 1);
        height = Math.round(vertices.lastKey().getY() + 1);
    }
    
    
    @Override
    public GridMappableType place(int x, int y, GridMappableType gridMappableType)
    {
        return place(calculateIndex(x, y), gridMappableType);
    }
    
    
    @Override
    public int getWidth()
    {
        return width;
    }
    
    
    @Override
    public Tile[][] getVertexGrid()
    {
        Tile[][] tileGrid = new Tile[height][width];
        
        for (Tile tile : verticeSet())
        {
            tileGrid[tile.getY()][tile.getX()] = tile;
        }
        
        
        return tileGrid;
    }
    
    
    /**
     * Moves the element from {@code from} to {@code to}.
     *
     * @param from The Tile to move the element from.
     * @param to   The Tile to move the element to.
     * @return The element that was moved.
     */
    public GridMappableType moveElement(Tile from, Tile to)
    {
        GridMappableType element = clearVertex(from);
        place(to, element);
        return element;
    }
}
