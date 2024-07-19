package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.GridMappable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class GridMappableInfoPane<Type extends GridMappable> extends NameDescribableInfoPane<Type>
{
    
    public GridMappableInfoPane(Type gridMappable)
    {
        super(gridMappable);
        Rectangle spriteRectangle = spriteRectangle(gridMappable);
        
        getChildren().add(spriteRectangle);
    }
    
    
    private Rectangle spriteRectangle(Type gridMappable)
    {
        Image image = gridMappable.getSprite();
        Rectangle rectangle = new Rectangle(image.getWidth(), image.getHeight(), new ImagePattern(image));
        rectangle.setOpacity(0.5);
        return rectangle;
    }
}
