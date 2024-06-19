package com.gitgud.engine.view;

import com.gitgud.engine.model.gameObject.Sprite;
import com.gitgud.engine.model.map.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Pattern;


public class SpriteHelper
{
    private SpriteHelper()
    {}
    
    
    public static <SpriteType extends Sprite> HashMap<SpriteType, ImagePattern> loadImagePatterns(
            Collection<SpriteType> spriteCollection)
    {
        HashMap<SpriteType,ImagePattern> patterns=new HashMap<>();
        
        for (SpriteType sprite : spriteCollection)
        {
            if (sprite == null)
            {
                continue;
            }
            patterns.put(sprite, new ImagePattern(new Image(sprite.getSpriteUrl())));
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
        return SpriteHelper.createRectangle(pattern, (int) (tileSize*tile.getX()),
                                            (int) (tileSize*tile.getY()), tileSize, tileSize);
        
    }
}
