package com.gitgud.pieces.view.render.mission.MissionRender;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.*;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionHud;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;


public class MissionRender extends BaseActionContextRender<Mission, GameObject,MissionHud>
{
    
    public MissionRender(Mission mission)
    {
        super(mission, new MissionHud(mission));
    }
    
    
    
    @Override
    public void updateRender()
    {
        updatePlayerAgentRender();
        getHud().updateRender();
    }
    
    
    @Override
    public void render(Mission data)
    {
        renderPlayerAgent();
        super.render(data);
    }
    
    
    private void renderPlayerAgent()
    {
        PlayerAgent playerAgent = getData().getPlayerAgent();
        Tile playerAgentPosition = getData().getPlayerAgentPosition();
        getGridMapRender().addGridMappable(playerAgent, playerAgentPosition);
    }
    
    
    private void updatePlayerAgentRender()
    {
        PlayerAgent playerAgent = getData().getPlayerAgent();
        Tile playerAgentPosition = getData().getPlayerAgentPosition();
        getGridMapRender().relocateGridMappable(playerAgent, playerAgentPosition);
    }
    
}
