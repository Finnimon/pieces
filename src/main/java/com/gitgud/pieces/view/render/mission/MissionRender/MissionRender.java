package com.gitgud.pieces.view.render.mission.MissionRender;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.engine.view.Hud;
import com.gitgud.pieces.view.render.mission.MissionHud;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;


public class MissionRender extends AnchorPane implements ActionContextRender<Mission,GameObject>
{
    
    
    public static final int GRID_MAP_SCROLL_PANE_WIDTH = 1500;
    
    
    public static final int GRID_MAP_SCROLL_PANE_HEIGHT = 900;
    
    
    private static final int TILE_SIZE = 90;
    
    
    private static final int WIDTH = 1800;
    
    
    private static final int HEIGHT = 1000;
    
    
    public static final double GRID_MAP_SCROLL_PANE_LEFT_ANCHOR = 200.0;
    
    
    private final Mission mission;
    
    
    private final GridMapRender<GameObject> gridMapRender;
    
    
    private final MissionHud missionHud;
    
    
    public MissionRender(Mission mission)
    {
        this.mission = mission;
        this.gridMapRender = new GridMapRender<>(mission.getGridMap(), TILE_SIZE);
        this.missionHud = new MissionHud(mission);
        render(mission);
    }
    
    
    @Override
    public void render(Mission data)
    {
        setDimensions();
        
        ObservableList<Node> children = getChildren();
        
        children.clear();
        
        addGridMapRender(children);
        
        children.add(missionHud);
        
        renderPlayerAgent();
    }
    
    
    private void addGridMapRender(ObservableList<Node> children)
    {
        ScrollPane scrollPane =new ScrollPane(gridMapRender);
        
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        scrollPane.setMinSize(GRID_MAP_SCROLL_PANE_WIDTH, GRID_MAP_SCROLL_PANE_HEIGHT);
        scrollPane.setPrefSize(GRID_MAP_SCROLL_PANE_WIDTH, GRID_MAP_SCROLL_PANE_HEIGHT);
        scrollPane.setMaxSize(GRID_MAP_SCROLL_PANE_WIDTH, GRID_MAP_SCROLL_PANE_HEIGHT);
        
        children.add(scrollPane);
        setLeftAnchor(scrollPane, GRID_MAP_SCROLL_PANE_LEFT_ANCHOR);
    }
    
    
    private void setDimensions()
    {
        setMinSize(WIDTH,HEIGHT);
        setPrefSize(WIDTH,HEIGHT);
        setMaxSize(WIDTH,HEIGHT);
    }
    
    
    @Override
    public void updateRender()
    {
        updatePlayerAgentRender();
        missionHud.updateRender();
    }
    
    
    @Override
    public Mission getData()
    {
        return mission;
    }
    
    
    private void renderPlayerAgent()
    {
        PlayerAgent playerAgent = getData().getPlayerAgent();
        Tile playerAgentPosition = getData().getPlayerAgentPosition();
        gridMapRender.addGridMappable(playerAgent, playerAgentPosition);
    }
    
    
    private void updatePlayerAgentRender()
    {
        PlayerAgent playerAgent = getData().getPlayerAgent();
        Tile playerAgentPosition = getData().getPlayerAgentPosition();
        gridMapRender.relocateGridMappable(playerAgent, playerAgentPosition);
    }
    
    
    @Override
    public Hud<Mission> getHud()
    {
        return this.missionHud;
    }
    
    
    @Override
    public GridMapRender<GameObject> getGridMapRender()
    {;
        return this.gridMapRender;
    }
}
