package com.gitgud.pieces.model.gameobjects.interactable.buildings;

import com.gitgud.engine.model.gameobjects.interactable.GridMappableBuilding;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;


public class Portal extends GridMappableBuilding<MissionController>
{
    private static final String NAME = "Portal";
    private static final String DESCRIPTION = "Travel through the portal.";
    private static final String SPRITE_FILEPATH = "src/main/resources/com/gitgud/sprites/interactables/Portal.png";
    
    private final Tile destination;
    public Portal(Tile destination)
    {
        super(NAME, DESCRIPTION, SPRITE_FILEPATH);
        this.destination = destination;
    }
    
    
    @Override
    public void interact(MissionController missionController)
    {
        Mission mission= missionController.getModel();
        mission.setPlayerAgentPosition(destination);
        PlayerAgent playerAgent = mission.getPlayerAgent();
        missionController.getRender().getGridMapRender().relocateGridMappable(playerAgent, destination);
    }
    
    
    public Tile getDestination()
    {
        return destination;
    }
}
