package com.gitgud.engine.model.map;

import com.gitgud.graph.RectangularGraph;
import com.gitgud.graph.WeightedEdge;
import com.gitgud.graph.WeightedGraph;

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
public class GridMap<GridMappable extends com.gitgud.engine.model.gameObject.GridMappable> extends WeightedGraph<Tile, GridMappable> implements RectangularGraph<Tile, GridMappable>
{
//    private final float tileSpacing;
    
    
    private int width;
    
    
    private int height;
    
    
//    public GridMap(TreeMap<Tile, GridMappable> vertices, TreeMap<Tile, HashSet<WeightedEdge<Tile>>> edges,
//                   float tileSpacing)
//    {
//        super(vertices, edges);
//        this.tileSpacing = tileSpacing;
//        updateDimensions();
//    }
    
    
    public GridMap(TreeMap<Tile, GridMappable> vertices, TreeMap<Tile, HashSet<WeightedEdge<Tile>>> edges)
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
    
    
    public static <GridmappableType extends com.gitgud.engine.model.gameObject.GridMappable> GridMap<GridmappableType> create(
            TerrainType[][] terrain)
    {
        //todo for first instance creation
        return null;
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
        TreeMap<Tile, GridMappable> vertices = this.getVertices();
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
    public GridMappable get(double x, double y)
    {
        return get(getVertex(x, y));
    }
}
