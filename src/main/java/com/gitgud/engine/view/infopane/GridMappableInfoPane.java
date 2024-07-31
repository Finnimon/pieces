package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.GridMappable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @param <Type>
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class GridMappableInfoPane<Type extends GridMappable> extends NameDescribableInfoPane<Type>
{
    
    public GridMappableInfoPane(Type gridMappable)
    {
        super(gridMappable);
        ImageView imageView = spriteNode(gridMappable);
        getChildren().add(imageView);
        imageView.toBack();
        setAlignment(imageView, Pos.TOP_RIGHT);
    }
    
    
    private ImageView spriteNode(Type gridMappable)
    {
        Image image = gridMappable.getSprite();
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
