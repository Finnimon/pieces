package com.gitgud.engine.model.map;

import com.gitgud.graph.RectangularGraph;
import com.gitgud.graph.WeightedEdge;
import com.gitgud.graph.WeightedGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeMap;


/**
 * The default Graph for 2D Maps. To ensure proper funcionality maintain rectangularity. it's Vertex {@link Tile} extend {@link javafx.geometry.Point2D}.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 2.0
 */
public class GridMap<GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable> extends WeightedGraph<Tile, GridMappableType> implements RectangularGraph<Tile, GridMappableType>
{
    //    private final float tileSpacing;
    
    
    private int width;
    
    
    private int height;
    
    
    //    public GridMap(TreeMap<Tile, GridMappableType> vertices, TreeMap<Tile, HashSet<WeightedEdge<Tile>>> edges,
    //                   float tileSpacing)
    //    {
    //        super(vertices, edges);
    //        this.tileSpacing = tileSpacing;
    //        updateDimensions();
    //    }
    
    
    public GridMap(TreeMap<Tile, GridMappableType> vertices, TreeMap<Tile, HashSet<WeightedEdge<Tile>>> edges)
    {
        super(vertices, edges);
        //        this.tileSpacing = 1;
        updateDimensions();
    }
    
    
    public GridMap()
    {
        //        this.tileSpacing = 1;
        updateDimensions();
    }
    
    
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
    
    
    public static <GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable> GridMap<GridMappableType> create(
            boolean[][] grid, GridMappableType[] gridMappables)
    {
        GridMap<GridMappableType> gridMap = create(grid);
        for (int y = 0; y < gridMap.height; y++)
        {
            for (int x = 0; x < gridMap.width; x++)
            {
                gridMap.getVertices().put(gridMap.getVertex(x, y), gridMappables[gridMap.calculateIndex(x, y)]);
            }
        }
        
        
        return gridMap;
    }
    
    
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
    
    
    private static Tile[][] tileGridFromTerrainTypeGrid(TerrainType[][] grid)
    {
        int height = grid.length;
        int width = grid[0].length;
        Tile[][] tileGrid = new Tile[height][width];
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                tileGrid[y][x] = Tile.create(x, y, new Terrain(grid[y][x]), width);
            }
        }
        return tileGrid;
    }
    
    
    private void drawConcludableEdges()
    {
        for (Tile tile : getVertices().keySet())
        {
            if (!tile.getTerrain().isTraversable())
            {
                getEdgeMap().put(tile, new HashSet<>());
                continue;
            }
            
            Collection<Tile> neighbors = getNeighbors(tile);
            
            for (Tile neighbor : neighbors.stream().filter(n -> n.getTerrain().isTraversable()).toList())
            {
                addEdge(tile, new WeightedEdge<>(neighbor, (float) tile.distance(neighbor)));
            }
        }
    }
    
    
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
    
    
    @Override
    public int getWidth()
    {
        return width;
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
        width = (int) Math.round(vertices.lastKey().getX() + 1);
        height = (int) Math.round(vertices.lastKey().getY() + 1);
    }
    
    
    @Override
    public boolean isValid()
    {
        for (Tile tile : verticeSet())
        {
            if (!isVertexValid(tile))
            {
                return false;
            }
        }
        
        
        return true;
    }
    
    
    @Override
    public boolean isVertexValid(Tile vertex)
    {
        int index = vertex.getIndex();
        
        boolean isValid = getVertex(index) == vertex;
        
        if (!isValid)
        {
            return false;
        }
        
        double x = vertex.getX();
        double y = vertex.getY();
        
        isValid = Math.round(x + y * width) != index;
        //        isValid = Math.round((x + y * width) / tileSpacing) != index;
        
        
        return isValid;
    }
    
    
    @Override
    public Tile getVertex(double x, double y)
    {
        return getVertex(calculateIndex(x, y));
    }
    
    
    @Override
    public GridMappableType get(double x, double y)
    {
        return get(getVertex(x, y));
    }
    
    
    @Override
    public Tile[][] getVertexGrid()
    {
        Tile[][] tileGrid = new Tile[height][width];
        
        for (Tile tile : verticeSet())
        {
            tileGrid[(int) tile.getY()][(int) tile.getX()] = tile;
        }
        
        
        return tileGrid;
    }
    
    
    public GridMappableType moveElement(Tile from, Tile to)
    {
        GridMappableType element = clearVertex(from);
        place(to, element);
        return element;
    }
}
