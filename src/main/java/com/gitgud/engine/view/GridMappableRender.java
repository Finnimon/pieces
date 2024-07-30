package com.gitgud.engine.view;

import com.gitgud.engine.model.attackDefenseLogic.Defender;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.events.AppendRemoveNodeOnMouseEvent;
import com.gitgud.engine.view.infopane.GridMappableInfoPane;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


public class GridMappableRender<GridMappableType extends GridMappable> extends StackPane
        implements Render<GridMappableType>
{
    
    public GridMappableRender(GridMappableType gridMappable)
    {
        this(gridMappable, 0, 0);
    }
    
    
    public GridMappableRender(GridMappableType gridMappable, double x, double y)
    {
        this(gridMappable, x, y, gridMappable.getSprite().getWidth(), gridMappable.getSprite().getHeight());
    }
    
    
    public GridMappableRender(GridMappableType gridMappable, double x, double y, double width, double height)
    {
        setSize(width, height);
        setX(x);
        setY(y);
        render(gridMappable);
    }
    
    
    private void setSize(double width, double height)
    {
        setMaxWidth(width);
        setPrefWidth(width);
        setMinWidth(width);
        setMaxHeight(height);
        setPrefHeight(height);
        setMinHeight(height);
    }
    
    
    public final void setX(double x)
    {
        this.setTranslateX(x);
    }
    
    
    public final void setY(double y)
    {
        setTranslateY(y);
    }
    
    
    @Override
    public void render(GridMappableType data)
    {
        ImageView image = new ImageView(data.getSprite());
        image.setPreserveRatio(true);
        image.fitHeightProperty().bind(this.heightProperty());
        image.maxWidth(this.widthProperty().get());
        image.setSmooth(false);
        
        AppendRemoveNodeOnMouseEvent.add(this, new GridMappableInfoPane<>(data));
        getChildren().add(image);
        setAlignment(image, Pos.TOP_CENTER);
        
        if (!(data instanceof Defender defender))
        {
            return;
        }
        renderDefender(defender, image);
    }
    
    
    private void renderDefender(Defender defender, ImageView image)
    {
        ValueOfBar valueOfBar = ValueOfBar.healthBar(defender);
        getChildren().add(valueOfBar);
        image.fitHeightProperty().unbind();
        image.fitHeightProperty().bind(heightProperty().subtract(valueOfBar.heightProperty()));
        valueOfBar.setMaxHeight(Region.USE_PREF_SIZE);
        valueOfBar.setMinHeight(Region.USE_PREF_SIZE);
        valueOfBar.setMaxWidth(Region.USE_PREF_SIZE);
        valueOfBar.setMinWidth(Region.USE_PREF_SIZE);
        valueOfBar.prefWidthProperty().bind(this.widthProperty());
        valueOfBar.prefHeightProperty().bind(this.heightProperty().divide(8));
        setAlignment(valueOfBar, Pos.BOTTOM_CENTER);
    }
    
    
    public GridMappableRender(GridMappableType gridMappable, double size)
    {
        this(gridMappable, 0, 0, size);
    }
    
    
    public GridMappableRender(GridMappableType gridMappable, double x, double y, double size)
    {
        this(gridMappable, x, y, size, size);
    }
}
