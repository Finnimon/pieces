package com.gitgud.pieces.control;

import com.gitgud.engine.control.GridMovableController;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;


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
        Tile oldTile = mission.getPlayerAgentPosition();
        mission.setPlayerAgentPosition(tile);
        
        
        return oldTile;
    }
    
    
    @Override
    public GridMap<GameObject> getGridMap()
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
