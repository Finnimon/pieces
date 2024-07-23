package com.gitgud.pieces.model.gameobjects.interactable.buildings;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.GridMappableBuilding;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.engine.view.GridMappableRender;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;
import javafx.scene.input.MouseEvent;


public class Portal extends GridMappableBuilding<MissionController>
{
    protected static final String SPRITE_FILEPATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\interactable\\Portal.png";
    
    
    private static final String NAME = "Portal";
    
    
    private static final String DESCRIPTION = "Travel through the portal.";
    
    
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
        
        GridMap<GameObject> gridMap = mission.getGridMap();
        
        PlayerAgent playerAgent = mission.getPlayerAgent();
        
        GridMapRender<GameObject> gridMapRender = missionController.getRender().getGridMapRender();
        
        Tile position = gridMap.getNeighbors(destination).stream().filter(
                tile -> tile.getTerrain().isTraversable() && gridMap.get(tile) == null).findFirst().orElse(null);
        
        missionController.getRender().getGridMapRender().relocateGridMappable(playerAgent, position);
        
        
        mission.setPlayerAgentPosition(position);
        
        
        if (gridMap.get(destination) instanceof Portal) return;
        
        //add new Portal on the other side otherwise
        Tile newDestination = gridMap.getVertex(this);
        Portal destinationPortal = new Portal(newDestination);
        gridMap.place(destination, destinationPortal);
        gridMapRender.addGridMappable(destinationPortal, destination);
        
        GridMappableRender<GameObject> destinationPortalRender = gridMapRender.getGridMappableRender(destinationPortal);
        
        destinationPortalRender.addEventHandler(MouseEvent.MOUSE_CLICKED,
                                                MissionRender.interactableEventHandler(destinationPortal,
                                                                                       missionController,
                                                                                       newDestination));
    }
    
    
    public Tile getDestination()
    {
        return destination;
    }
}
