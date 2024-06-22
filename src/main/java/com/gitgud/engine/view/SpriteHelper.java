package com.gitgud.engine.view;

import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.model.map.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Collection;
import java.util.HashMap;


public class SpriteHelper
{
    private SpriteHelper()
    {
    }
    
    
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
            
            Image image = new Image(sprite.getSpriteUrl());
            
            patterns.put(sprite, new ImagePattern(image));
        }
        return patterns;
    }
    
    
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
    
    
    public static Rectangle createRectangle(Paint pattern, Tile tile, int tileSize)
    {
        return SpriteHelper.createRectangle(pattern, (int) (tileSize * tile.getX()), (int) (tileSize * tile.getY()),
                                            tileSize, tileSize);
        
    }
}
