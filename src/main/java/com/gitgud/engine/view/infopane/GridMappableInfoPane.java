package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.SpriteHelper;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private final Rectangle spriteRectangle(Type gridMappable)
    {
        Image image= new Image(gridMappable.getSpriteUrl());
        Rectangle rectangle = new Rectangle(image.getWidth(), image.getHeight(), new ImagePattern(image));
        rectangle.setOpacity(0.5);
        return rectangle;
    }
}
