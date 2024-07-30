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
    
    
    /**
     * The terrain of the {@link Tile}.
     */
    private final Terrain terrain;
    
    
    /**
     * The x coordinate of the {@link Tile}.
     */
    private final int x;
    
    
    /**
     * The y coordinate of the {@link Tile}.
     */
    private final int y;
    
    
    /**
     * The index of the {@link Tile}.
     *
     * @see com.gitgud.graph.Vertex
     */
    private int index;
    
    
    /**
     * Constructor that assigns all attributes and calculates the index aswell as wraps the {@link TerrainType}.
     *
     * @param x           The x coordinate of the Tile.
     * @param y           The y coordinate of the Tile.
     * @param terrainType The TerrainType of the Tiles Terrain.
     * @param width       The Width of the Enclosing GridMap.
     */
    public Tile(int x, int y, @NotNull TerrainType terrainType, int width)
    {
        this(x, y, new Terrain(terrainType), x + width * y);
    }
    
    
    /**
     * Default Constructor that assigns all attributes.
     *
     * @param x       The x coordinate of the Tile.
     * @param y       The y coordinate of the Tile.
     * @param terrain The terrain of the Tile.
     * @param index   The index of the Tile.
     */
    public Tile(int x, int y, @NotNull Terrain terrain, int index)
    {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.index = index;
    }
    
    
    @Override
    public int getIndex()
    {
        return index;
    }
    
    
    @Override
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    
    /**
     * Getter for the Terrain of the Tile.
     *
     * @return The Terrain of the Tile.
     */
    public @NotNull Terrain getTerrain()
    {
        return terrain;
    }
    
    
    @Override
    public @NotNull String getSpriteFilePath()
    {
        return determineSpriteFilePath();
    }
    
    
    /**
     * Determines the sprite file path of the {@link Tile}.
     *
     * @return
     */
    private @NotNull String determineSpriteFilePath()
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
