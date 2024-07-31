package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.Sprite;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * @param <Type>
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class GridMappableInfoPane<Type extends GridMappable> extends NameDescribableInfoPane<Type>
{
    /**
     * <p>Defaults to {@link NameDescribableInfoPane#NameDescribableInfoPane(Named)}.
     * <p>Also adds a view of the {@link Sprite} of the {@link GridMappable}.
     *
     * @param gridMappable The {@link GridMappable} to be displayed in the {@link GridMappableInfoPane}.
     * @see NameDescribableInfoPane#NameDescribableInfoPane(Named)
     */
    public GridMappableInfoPane(Type gridMappable)
    {
        super(gridMappable);
        ImageView imageView = spriteNode(gridMappable);
        getChildren().add(imageView);
        imageView.toBack();
        setAlignment(imageView, Pos.TOP_RIGHT);
    }
    
    
    /**
     * Creates a suitable Node to be displayed in the {@link GridMappableInfoPane}.
     *
     * @param sprite The Sprite to be displayed.
     * @return The ImageView of the Sprite.
     */
    private static ImageView spriteNode(Sprite sprite)
    {
        Image image = sprite.getSprite();
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);
        
        return imageView;
    }
}
