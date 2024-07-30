package com.gitgud.engine.view;

import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;


public abstract class BaseActionContextRender<MType extends ActionAwaiterModel<GMType>, GMType extends GridMappable,
        HudType extends ActionContextHud<MType>>
        extends StackPane implements ActionContextRender<MType, GMType>
{
    public static final int MINIMUM_TILE_SIZE = 50;
    
    
    public static final int MAX_GRIDMAP_SIZE = 1000;
    
    
    private final MType data;
    
    
    private final ScrollPane gridMapRenderScrollPane;
    
    
    private final HudType hud;
    
    
    private final GridMapRender<GMType> gridMapRender;
    
    
    public BaseActionContextRender(MType data, HudType hud)
    {
        this(data, determineOptimumTileSize(data.getGridMap()), hud);
    }
    
    
    public BaseActionContextRender(MType data, int tileSize, HudType hud)
    {
        this.data = data;
        this.hud = hud;
        this.gridMapRender = new GridMapRender<>(data.getGridMap(), tileSize);
        gridMapRenderScrollPane = new ScrollPane();
        
        
        render(data);
    }
    
    
    private static int determineOptimumTileSize(GridMap<?> gridMap)
    {
        int calculatedTileSize = MAX_GRIDMAP_SIZE / gridMap.getWidth();
        
        return Math.max(calculatedTileSize, MINIMUM_TILE_SIZE);
    }
    
    
    @Override
    public void render(MType data)
    {
        addGridMapRender();
        getChildren().add(hud);
    }
    
    
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
    public GridMapRender<GMType> getGridMapRender()
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
