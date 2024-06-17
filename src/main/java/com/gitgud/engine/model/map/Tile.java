package com.gitgud.engine.model.map;

import com.gitgud.engine.model.gameObject.Sprite;
import com.gitgud.graph.Vertex;
import com.gitgud.graph.Vertex2D;
import javafx.geometry.Point2D;


/**
 * The default Vertex implementation for {@link GridMap}
 * This Vertex offers a Sprite to be used on the {@link com.gitgud.engine.view.GridMapView}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 13.06.2024
 * @Version: 2.0
 */
public class Tile extends Vertex2D implements Sprite
{
    private static final String DIR_FILE_PATH = "src\\main\\resources\\com\\gitgud\\sprites\\tiles\\";
    
    
    private final Terrain terrain;
    
    
    private final String spriteFilePath;
    
    
    private int index;
    
    
    /**
     * Creates a new instance of {@code Point2D}.
     *
     * @param x       the x coordinate of the point
     * @param y       the y coordinate of the point
     * @param terrain
     */
    private Tile(double x, double y, Terrain terrain)
    {
        super(x, y);
        this.terrain = terrain;
        spriteFilePath = determineSpriteFilePath();
    }
    
    
    public int getIndex()
    {
        return index;
    }
    
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    
    public Tile create(int x, int y, String terrainType)
    {
        Terrain terrain = new Terrain(TerrainType.valueOf(terrainType.trim().toUpperCase()));
        return new Tile(x, y, terrain);
    }
    
    
    public Tile create(int x, int y)
    {
        return create(x, y, TerrainType.TRAVERSABLE.name());
    }
    
    
    public Terrain getTerrain()
    {
        return terrain;
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
    
    private String determineSpriteFilePath()
    {
        StringBuilder stringBuilder = new StringBuilder(DIR_FILE_PATH);
        
        stringBuilder.append(terrain.terrainType().name());
        
        boolean isWhite = (getX() + getY()) % 2 == 0;
        
        if (isWhite)
        {
            stringBuilder.append("white\\");
        }
        else
        {
            stringBuilder.append("black\\");
        }
        
        return stringBuilder.toString();
    }
}
