package com.gitgud.model.mission;

import com.gitgud.model.gameObjects.PlayerAgent;
import com.gitgud.model.mission.map.GridMap;
import com.gitgud.model.mission.map.GridMapping;
import com.gitgud.model.mission.map.Tile;
import com.gitgud.utility.interfaces.Interacteble;

import java.util.HashMap;


public class Mission extends GridMapContext<Interacteble>
{
    private final PlayerAgent playerFigure;
    
    
    private final GridMapping<PlayerAgent> playerAgentGridMapping;
    
    
    public Mission(GridMap gridMap, HashMap<Interacteble, Tile> gridMappings, PlayerAgent playerFigure,
                   GridMapping<PlayerAgent> playerAgentGridMapping)
    {
        super(gridMap, gridMappings);
        this.playerFigure = playerFigure;
        this.playerAgentGridMapping = playerAgentGridMapping;
    }
    
    
    public PlayerAgent getPlayerFigure()
    {
        return playerFigure;
    }
    
    
    public GridMapping<PlayerAgent> getPlayerAgentGridMapping()
    {
        return playerAgentGridMapping;
    }
}
