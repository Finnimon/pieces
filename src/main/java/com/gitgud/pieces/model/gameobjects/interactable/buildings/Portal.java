package com.gitgud.pieces.model.gameobjects.interactable.buildings;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.GridMappableBuilding;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;


public class Portal extends GridMappableBuilding<MissionController>
{
    private static final String NAME = "Portal";
    
    
    private static final String DESCRIPTION = "Travel through the portal.";
    
    
    protected static final String SPRITE_FILEPATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\interactable\\Portal.png";
    
    
    private final Tile destination;
    
    
    public Portal(Tile destination)
    {
        super(NAME, DESCRIPTION, SPRITE_FILEPATH);
        this.destination = destination;
    }
    
    
    @Override
    public void interact(MissionController missionController)
    {
        Mission mission = missionController.getModel();
        Tile position = mission.getPlayerAgentPosition();
        
        GridMap<GameObject> gridMap = mission.getGridMap();
        
        PlayerAgent playerAgent = mission.getPlayerAgent();
        
        mission.setPlayerAgentPosition(destination);
        
        GridMapRender<GameObject> gridMapRender = missionController.getRender().getGridMapRender();
        
        missionController.getRender().getGridMapRender().relocateGridMappable(playerAgent, destination);
        
        if (gridMap.get(destination) instanceof Portal)
        {
            return;
        }
        //add new Portal on the other side otherwise
        Portal destinationPortal = new Portal(position);
        gridMap.place(destination, destinationPortal);
        gridMapRender.addGridMappable(destinationPortal, destination);
    }
    
    
    public Tile getDestination()
    {
        return destination;
    }
}
