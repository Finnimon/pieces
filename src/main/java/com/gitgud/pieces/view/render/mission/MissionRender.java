package com.gitgud.pieces.view.render.mission;

import com.gitgud.engine.model.gameObject.Sprite;
import com.gitgud.engine.model.gameObject.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.engine.view.UpdatableRender;
import com.gitgud.pieces.model.gameObjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.Collection;


public class MissionRender extends Group implements UpdatableRender<Mission>
{
    private static final int TILE_SIZE = 100;
    
    
    private static final int WIDTH = 1920;
    
    
    private static final int HEIGHT = 1080;
    
    
    
    private final Mission mission;
    
    
    private final GridMapRender<Sprite> gridMapRender;
    
    
    private final MissionHud missionHud;
    
    
    public MissionRender(Mission mission)
    {
        this.mission = mission;
        this.gridMapRender = new GridMapRender<Sprite>(mission.getGridMap(), TILE_SIZE);
        this.missionHud = new MissionHud(mission);
    }
    
    
    
    
    @Override
    public void render(Mission data)
    {
        ObservableList<Node> children= getChildren();

        children.clear();
        children.add(gridMapRender);
        children.add(missionHud);
        renderPlayerAgent();
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
    
    
    
}
