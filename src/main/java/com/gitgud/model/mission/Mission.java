package com.gitgud.model.mission;

import com.gitgud.model.map.GridMapContext;
import com.gitgud.model.gameObjects.gridMovable.PlayerAgent;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.GridMapping;
import com.gitgud.model.map.Tile;
import com.gitgud.model.gameObjects.interacteble.Interacteble;

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
