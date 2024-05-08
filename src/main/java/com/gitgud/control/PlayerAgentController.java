package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.Agent;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.gameObjects.gridMovable.PlayerAgent;
import com.gitgud.model.mission.Mission;
import com.gitgud.model.map.GridMapping;
import com.gitgud.model.map.Tile;


public class PlayerAgentController extends GridMovableController
{
    public PlayerAgentController(Mission mission, GridMapping<FightAgent> gridMapping)
    {
        super(mission, gridMapping);
    }
    
    
    public PlayerAgentController(Mission mission, Agent agent)
    {
        super(mission, mission.getPlayerAgentGridMapping());
    }
    
    
    @Override
    public Mission getGridMapContext()
    {
        return (Mission) super.getGridMapContext();
    }
    
    
    @Override
    public GridMapping<PlayerAgent> getGridMapping()
    {
        return super.getGridMapping();
    }
    
    
    @Override
    public Tile moveTo(Tile tile)
    {
        return super.moveTo(tile);
    }
}
