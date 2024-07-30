package com.gitgud.engine.model.map;

import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.graph.Vertex2D;
import org.jetbrains.annotations.NotNull;


/**
 * The default Vertex implementation for {@link GridMap}
 * This Vertex offers a Sprite to be used on the {@link GridMapRender}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 2.0
 */
public class Tile implements Vertex2D, Sprite
{
    
    
    public static final String WHITE = "\\white";
    
    
    public static final String BLACK = "\\black";
    
    
    private static final String DIR_FILE_PATH = "src\\main\\resources\\com\\gitgud\\engine\\model\\map\\tile\\";
    
    
    private final Terrain terrain;
    
    
    private final int x;
    
    
    private final int y;
    
    
    private int index;
    
    
    /**
     * Creates a new instance of {@code Point2D}.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Tile(int x, int y, Terrain terrain, int index)
    {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.index = index;
    }
    
    
    public static Tile create(int x, int y, TerrainType terrainType, int width)
    {
        return create(x, y, new Terrain(terrainType), width);
    }
    
    
    public static Tile create(int x, int y, Terrain terrain, int width)
    {
        return new Tile(x, y, terrain, x + width * y);
    }
    
    
    public static Tile create(int x, int y, int width)
    {
        return create(x, y, new Terrain(TerrainType.TRAVERSABLE), width);
    }
    
    
    public int getIndex()
    {
        return index;
    }
    
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    
    public Terrain getTerrain()
    {
        return terrain;
    }
    
    
    @Override
    public @NotNull String getSpriteFilePath()
    {
        return determineSpriteFilePath();
    }
    
    
    private String determineSpriteFilePath()
    {
        StringBuilder stringBuilder = new StringBuilder(DIR_FILE_PATH);
        
        stringBuilder.append(terrain.terrainType().name());
        
        boolean isWhite = (getX() + getY()) % 2 == 0;
        
        if (isWhite)
        {
            stringBuilder.append(WHITE);
        }
        else
        {
            stringBuilder.append(BLACK);
        }
        
        stringBuilder.append(DOT_PNG);
        
        return stringBuilder.toString();
    }
    
    
    @Override
    public int getX()
    {
        return x;
    }
    
    
    @Override
    public int getY()
    {
        return y;
    }
    
    
    @Override
    public int hashCode()
    {
        return index;
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Tile tile))
        {
            return false;
        }
        return hashCode() == tile.hashCode() &&
               tile.terrain.terrainType().equals(terrain.terrainType()) &&
               tile.x == x &&
               tile.y == y;
    }
}
