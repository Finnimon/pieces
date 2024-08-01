package com.gitgud.engine.view;

import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;


/**
 * This Class provides a base implementation for {@link ActionContextRender} for non-specialized needs.
 *
 * @param <MType>>  The type if {@link ActionAwaiterModel} used by this Render.
 * @param <GmType>> The Type of the {@link GridMappable} on this model's {@link GridMap}.
 * @param <HudType> The Type of the {@link ActionContextHud} used by this Render.
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public abstract class BaseActionContextRender<MType extends ActionAwaiterModel<GmType>, GmType extends GridMappable,
        HudType extends ActionContextHud<MType>>
        extends StackPane implements ActionContextRender<MType, GmType>
{
    /**
     * The default minimum Tile size for proper legibility.
     */
    public static final int MINIMUM_TILE_SIZE = 50;
    
    
    /**
     * The Maximum Width and Height of a {@link GridMapRender}.
     */
    public static final int MAX_GRIDMAP_SIZE = 1000;
    
    
    /**
     * The Data to be rendered.
     */
    private final MType data;
    
    
    /**
     * The Scroll Pane for the {@link GridMapRender}. This allows for scrolling on very large GridMaps.
     */
    private final ScrollPane gridMapRenderScrollPane;
    
    
    /**
     * The Hud of this Render.
     */
    private final HudType hud;
    
    
    /**
     * <p>The {@link GridMapRender} for this Render.
     * <p>Allows for faster access to gridMapRender instead of calling {@code gridMapRenderScrollPane.getContent()}
     */
    private final GridMapRender<GmType> gridMapRender;
    
    
    /**
     * Defaults to {@link #BaseActionContextRender(MType, int, HudType)} with
     * {@link #determineOptimumTileSize(GridMap)} as tileSize.
     *
     * @see #BaseActionContextRender(MType, int, HudType)
     * @see #determineOptimumTileSize(GridMap)
     */
    public BaseActionContextRender(@NotNull MType data, @NotNull HudType hud)
    {
        this(data, determineOptimumTileSize(data.getGridMap()), hud);
    }
    
    
    /**
     * Creates The Render and assigns all attributes to it.
     *
     * @param data     The {@link ActionAwaiterModel} to be rendered.
     * @param tileSize The tileSize for the {@link GridMapRender}.
     * @param hud      The {@link ActionContextHud} for this Render.
     */
    public BaseActionContextRender(@NotNull MType data, int tileSize, @NotNull HudType hud)
    {
        this.data = data;
        this.hud = hud;
        this.gridMapRender = new GridMapRender<>(data.getGridMap(), tileSize);
        gridMapRenderScrollPane = new ScrollPane();
        
        render(data);
    }
    
    
    /**
     * Calculates the optimum tile size based on the {@link GridMap} width, {@link #MAX_GRIDMAP_SIZE} and
     * {@link #MINIMUM_TILE_SIZE}.
     *
     * @param gridMap The {@link GridMap} to determine the optimum tile size for.
     * @return The optimum tile size for a {@link GridMapRender} of {@code gridMap}.
     */
    private static int determineOptimumTileSize(@NotNull GridMap<?> gridMap)
    {
        int calculatedTileSize = MAX_GRIDMAP_SIZE / gridMap.getWidth();
        
        return Math.max(calculatedTileSize, MINIMUM_TILE_SIZE);
    }
    
    
    @Override
    public void render(MType model)
    {
        addGridMapRender();
        getChildren().add(hud);
    }
    
    
    /**
     * Adds the {@link #gridMapRender} to the {@link #gridMapRenderScrollPane} and styles it.
     */
    private void addGridMapRender()
    {
        gridMapRenderScrollPane.setContent(gridMapRender);
        
        gridMapRenderScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gridMapRenderScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gridMapRenderScrollPane.setPannable(true);
        
        gridMapRenderScrollPane.setMaxSize(MAX_GRIDMAP_SIZE, MAX_GRIDMAP_SIZE);
        
        gridMapRenderScrollPane.setFitToWidth(true);
        setAlignment(gridMapRenderScrollPane, Pos.CENTER);
        
        gridMapRenderScrollPane.setVvalue(1);
        getChildren().add(gridMapRenderScrollPane);
    }
    
    
    @Override
    public GridMapRender<GmType> getGridMapRender()
    {
        return gridMapRender;
    }
    
    
    @Override
    public HudType getHud()
    {
        return hud;
    }
    
    
    @Override
    public void updateRender()
    {
        hud.updateRender();
    }
    
    
    @Override
    public MType getData()
    {
        return data;
    }
}
