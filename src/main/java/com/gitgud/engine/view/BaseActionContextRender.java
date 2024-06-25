package com.gitgud.engine.view;

import com.gitgud.engine.control.StageController;
import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class BaseActionContextRender<MType extends ActionAwaiterModel<GMType>, GMType extends GridMappable, HudType extends ActionContextHud<MType>> extends StackPane implements ActionContextRender<MType, GMType>
{
    
    public static final double MINIMUM_GRID_MAP_SCROLL_PANE_SIDE_ANCHOR = 200d;
    
    
    public static final double DEFAULT_GRID_MAP_SCROLL_PANE_BOTTOM_ANCHOR = 100d;
    
    
    public static final int TILE_SIZE_DEFAULT_VALUE = 90;
    
    
    private final MType data;
    
    
    private final ScrollPane gridMapRenderScrollPane;
    
    
    private final HudType hud;
    
    
    private final GridMapRender<GMType> gridMapRender;
    
    
    public BaseActionContextRender(MType data, int tileSize, HudType hud)
    {
        this.data = data;
        this.hud = hud;
        this.gridMapRender = new GridMapRender<>(data.getGridMap(), tileSize);
        gridMapRenderScrollPane = new ScrollPane();
        
        addGridMapRender();
        
        
        render(data);
    }
    
    
    public BaseActionContextRender(MType data, HudType hud)
    {
        this(data, determineOptimumTileSize(data.getGridMap()), hud);
    }
    
    
    private static int determineOptimumTileSize(GridMap<?> gridMap)
    {
        int width = gridMap.getWidth();
        int height = gridMap.getHeight();
        
        if (width == height && height < 20)
        {
            return 1000 / height;
        }
        
        return TILE_SIZE_DEFAULT_VALUE;
    }
    
    
    private void addGridMapRender()
    {
        gridMapRenderScrollPane.setContent(gridMapRender);
        
        gridMapRenderScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gridMapRenderScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gridMapRenderScrollPane.setMaxWidth(1000);
        gridMapRenderScrollPane.setMaxHeight(1000);
        setAlignment(gridMapRenderScrollPane, Pos.TOP_CENTER);
        
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
    public void render(MType data)
    {
        getChildren().add(hud);
        setAlignment(hud,Pos.TOP_LEFT);
        Stage stage = StageController.getInstance().getStage();
        
        stage.getScene().setRoot(this);
        
        if (stage.isFullScreen())
        {
            return;
        }
        
        stage.setFullScreen(true);
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
    
    //private final Mission mission;
    //
    //
    //    private final GridMapRender<GameObject> gridMapRender;
    //
    //
    //    private final MissionHud missionHud;
    //
    //
    //    public MissionRender(Mission mission)
    //    {
    //        this.mission = mission;
    //        this.gridMapRender = new GridMapRender<>(mission.getGridMap(), TILE_SIZE);
    //        this.missionHud = new MissionHud(mission);
    //        render(mission);
    //    }
    //
    //
    //    @Override
    //    public void render(Mission data)
    //    {
    //        setDimensions();
    //
    //        ObservableList<Node> children = getChildren();
    //
    //        children.clear();
    //
    //        addGridMapRender(children);
    //
    //        children.add(missionHud);
    //
    //        renderPlayerAgent();
    //    }
    //
    //
    //    private void addGridMapRender(ObservableList<Node> children)
    //    {
    //        ScrollPane scrollPane =new ScrollPane(gridMapRender);
    //
    //        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    //        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    //
    //        scrollPane.setMinSize(GRID_MAP_SCROLL_PANE_WIDTH, GRID_MAP_SCROLL_PANE_HEIGHT);
    //        scrollPane.setPrefSize(GRID_MAP_SCROLL_PANE_WIDTH, GRID_MAP_SCROLL_PANE_HEIGHT);
    //        scrollPane.setMaxSize(GRID_MAP_SCROLL_PANE_WIDTH, GRID_MAP_SCROLL_PANE_HEIGHT);
    //
    //        children.add(scrollPane);
    //        setLeftAnchor(scrollPane, GRID_MAP_SCROLL_PANE_LEFT_ANCHOR);
    //    }
    //
    //
    //    private void setDimensions()
    //    {
    //        setMinSize(WIDTH,HEIGHT);
    //        setPrefSize(WIDTH,HEIGHT);
    //        setMaxSize(WIDTH,HEIGHT);
    //    }
    //
    //
    //    @Override
    //    public void updateRender()
    //    {
    //        updatePlayerAgentRender();
    //        missionHud.updateRender();
    //    }
    //
    //
    //    @Override
    //    public Mission getData()
    //    {
    //        return mission;
    //    }
    //
    //
    //    private void renderPlayerAgent()
    //    {
    //        PlayerAgent playerAgent = getData().getPlayerAgent();
    //        Tile playerAgentPosition = getData().getPlayerAgentPosition();
    //        gridMapRender.addGridMappable(playerAgent, playerAgentPosition);
    //    }
    //
    //
    //    private void updatePlayerAgentRender()
    //    {
    //        PlayerAgent playerAgent = getData().getPlayerAgent();
    //        Tile playerAgentPosition = getData().getPlayerAgentPosition();
    //        gridMapRender.relocateGridMappable(playerAgent, playerAgentPosition);
    //    }
    //
    //
    //    @Override
    //    public ActionContextHud<Mission> getHud()
    //    {
    //        return this.missionHud;
    //    }
    //
    //
    //    @Override
    //    public GridMapRender<GameObject> getGridMapRender()
    //    {;
    //        return this.gridMapRender;
    //    }
    
}
