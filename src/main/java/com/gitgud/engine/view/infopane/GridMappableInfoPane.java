package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.GridMappable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GridMappableInfoPane<Type extends GridMappable> extends NameDescribableInfoPane<Type>
{
    
    public GridMappableInfoPane(Type gridMappable)
    {
        super(gridMappable);
        ImageView imageView = spriteNode(gridMappable);
        getChildren().add(imageView);
        imageView.toBack();
        imageView.opacityProperty().set(0.5);
        setAlignment(imageView, Pos.TOP_RIGHT);
    }
    
    
    private ImageView spriteNode(Type gridMappable)
    {
        Image image = gridMappable.getSprite();
        ImageView imageView = new ImageView(image);
        imageView.maxHeight(5);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
