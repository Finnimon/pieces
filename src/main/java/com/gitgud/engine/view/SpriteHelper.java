package com.gitgud.engine.view;

import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.model.map.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Collection;
import java.util.HashMap;


/**
 * Helper class for {@link Sprite}s
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class SpriteHelper
{
    private SpriteHelper()
    {
    }
    
    
    /**
     * Loads multiple ImagePatterns at once
     *
     * @param spriteCollection the collection of {@link Sprite}
     * @param <SpriteType>     the type of {@link Sprite}
     * @return a {@link HashMap} of {@link Sprite} to {@link ImagePattern}
     */
    public static <SpriteType extends Sprite> HashMap<SpriteType, ImagePattern> loadImagePatterns(
            Collection<SpriteType> spriteCollection)
    {
        HashMap<SpriteType, ImagePattern> patterns = new HashMap<>();
        int i = 0;
        for (SpriteType sprite : spriteCollection)
        {
            if (sprite == null)
            {
                continue;
            }
            
            Image image = sprite.getSprite();
            
            patterns.put(sprite, new ImagePattern(image));
        }
        return patterns;
    }
    
    
    /**
     * Creates a rectangle from a Tile.
     *
     * @param pattern  The Paint for the rectangle.
     * @param tile     The Tile.
     * @param tileSize The size of the Tile.
     * @return The Rectangle.
     */
    public static Rectangle createRectangle(Paint pattern, Tile tile, int tileSize)
    {
        return SpriteHelper.createRectangle(pattern,
                                            tileSize * tile.getX(),
                                            tileSize * tile.getY(),
                                            tileSize,
                                            tileSize);
        
    }
    
    
    /**
     * Creates a Rectangle with the specified values.
     *
     * @param pattern The Paint for the rectangle.
     * @param x The x coordinate of the rectangle.
     * @param y The y coordinate of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @return The created Rectangle.
     */
    public static Rectangle createRectangle(Paint pattern, int x, int y, int width, int height)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(pattern);
        return rectangle;
    }
}
