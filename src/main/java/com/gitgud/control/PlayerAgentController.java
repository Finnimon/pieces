package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.PlayerAgent;
import com.gitgud.model.gameObjects.interactable.Interactable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;
import com.gitgud.model.mission.Mission;


public class PlayerAgentController extends GridMovableController<PlayerAgent>
{
    private final Mission mission;
    
    
    public PlayerAgentController(Mission mission)
    {
        this.mission = mission;
    }
    
    
    @Override
    public Tile moveTo(Tile tile)
    {
        Mission mission = getMission();
        Tile oldTile=mission.getPlayerAgentPosition();
        mission.setPlayerAgentPosition(tile);
        
        
        return oldTile;
    }
    
    
    
    
    @Override
    public GridMap<Interactable> getGridMap()
    {
        return getMission().getGridMap();
    }
    
    
    @Override
    public PlayerAgent getGridMovable()
    {
        return getMission().getPlayerAgent();
    }
    
    
    @Override
    public Tile getPosition()
    {
        return getMission().getPlayerAgentPosition();
    }
    
    
    public Mission getMission()
    {
        return mission;
    }
}
