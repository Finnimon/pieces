package com.gitgud.engine.view;

import com.gitgud.engine.model.attackDefenseLogic.Health;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.view.infopane.GridMappableInfoPane;
import com.gitgud.engine.view.utility.AppendRemoveNodeOnMouseEventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;


/**
 * <p>Creates a Render of any {@link GridMappable} on the {@link GridMappableRender}.
 * <p>Is Able to Render GridMappable and even those that implement {@link Health} as well.
 *
 * @param <GridMappableType> The type of the {@link GridMappable}.
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.1
 */
public class GridMappableRender<GridMappableType extends GridMappable> extends StackPane
        implements Render<GridMappableType>
{
    /**
     * The Proportional Height of a HealthBar if this {@link GridMappable} implements {@link Health}
     */
    public static final float PROPORTIONAL_VALUEOFBAR_HEIGHT = 1f / 6;
    
    
    public GridMappableRender(GridMappableType gridMappable, double size)
    {
        this(gridMappable, 0, 0, size);
    }
    
    
    /**
     * Defaults to {@link #GridMappableRender(GridMappable, double, double, double, double)} with {@code size} being
     * both the width and height.
     *
     * @param gridMappable The {@link GridMappable} to be rendered.
     * @param x            The x coordinate of the {@link GridMappableRender}.
     * @param y            The y coordinate of the {@link GridMappableRender}.
     * @param size         The width and height of the {@link GridMappableRender}.
     */
    public GridMappableRender(@NotNull GridMappableType gridMappable, double x, double y, double size)
    {
        this(gridMappable, x, y, size, size);
    }
    
    
    /**
     * <p>Defaults to {@link GridMappableRender#GridMappableRender(GridMappable, double, double)} and also sets
     * translateX and translateY
     *
     * @param gridMappable The {@link GridMappable} to be rendered.
     * @param x            The x coordinate of the {@link GridMappableRender}.
     * @param y            The y coordinate of the {@link GridMappableRender}.
     * @param width        The width of the {@link GridMappableRender}.
     *                     <p>The Ratio of the image will be preserved so this is only the width of the parent Pane.
     * @param height       The height of the {@link GridMappableRender}.
     *                     <p>The Ratio of the image will be preserved so this is only the height of the parent Pane.
     * @see #GridMappableRender(GridMappable, double, double)
     */
    public GridMappableRender(@NotNull GridMappableType gridMappable, double x, double y, double width, double height)
    {
        this(gridMappable, width, height);
        setTranslateX(x);
        setTranslateY(y);
    }
    
    
    /**
     * <p>Defaults to {@link GridMappableRender#GridMappableRender(GridMappable)} and also sets the size.
     *
     * @param gridMappable The {@link GridMappable} to be rendered.
     * @param width        The width of the {@link GridMappableRender}.
     *                     <p>The Ratio of the image will be preserved so this is only the width of the parent Pane.
     * @param height       The height of the {@link GridMappableRender}.
     *                     <p>The Ratio of the image will be preserved so this is only the height of the parent Pane.
     * @see #GridMappableRender(GridMappable)
     */
    public GridMappableRender(@NotNull GridMappableType gridMappable, double width, double height)
    {
        this(gridMappable);
        setSize(width, height);
    }
    
    
    /**
     * <p>Default constructor for {@link GridMappableRender}.
     * <p>Does not do any sizing or positioning.
     *
     * @param gridMappable The {@link GridMappable} to be rendered.
     */
    public GridMappableRender(@NotNull GridMappableType gridMappable)
    {
        render(gridMappable);
        setPickOnBounds(true);//because the image might be resized smaller than the allocated size.
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
    
    
    @Override
    public void render(@NotNull GridMappableType model)
    {
        ImageView imageView = new ImageView(model.getSprite());
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(this.heightProperty());
        imageView.maxWidth(this.widthProperty().get());
        imageView.setSmooth(false);
        
        AppendRemoveNodeOnMouseEventHandler.add(this, new GridMappableInfoPane<>(model));
        getChildren().add(imageView);
        setAlignment(imageView, Pos.TOP_CENTER);
        
        if (!(model instanceof Fighter fighter))
        {
            return;
        }
        renderFighter(fighter, imageView);
    }
    
    
    private void renderFighter(Fighter fighter, ImageView imageView)
    {
        var healthBar = ValueOfBar.healthBar(fighter);
        var manaBar = ValueOfBar.manaBar(fighter);
        getChildren().addAll(healthBar, manaBar);
        
        imageView.fitHeightProperty().unbind();
        imageView.fitHeightProperty()
                 .bind(heightProperty().subtract(healthBar.heightProperty().add(manaBar.heightProperty())));
        
        healthBar.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        healthBar.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        manaBar.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        manaBar.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        
        healthBar.prefWidthProperty().bind(this.widthProperty());
        healthBar.prefHeightProperty().bind(this.heightProperty().multiply(PROPORTIONAL_VALUEOFBAR_HEIGHT));
        healthBar.translateYProperty().bind(manaBar.heightProperty().multiply(-1));
        setAlignment(healthBar, Pos.BOTTOM_CENTER);
        
        manaBar.prefWidthProperty().bind(this.widthProperty());
        manaBar.prefHeightProperty().bind(this.heightProperty().multiply(PROPORTIONAL_VALUEOFBAR_HEIGHT));
        setAlignment(manaBar, Pos.BOTTOM_CENTER);
    }
}
