package com.gitgud.model.mission;

import com.gitgud.model.gameObjects.gridMovable.PlayerAgent;
import com.gitgud.model.gameObjects.interactable.Interactable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.GridMapContext;
import com.gitgud.model.map.GridMapping;
import com.gitgud.model.map.Tile;

import java.util.HashMap;


public class Mission extends GridMapContext<Interactable>
{
    private final GridMapping<PlayerAgent> playerAgentGridMapping;
    
    
    public Mission(GridMap gridMap, HashMap<Interactable, Tile> gridMappings,
                   GridMapping<PlayerAgent> playerAgentGridMapping)
    {
        super(gridMap, gridMappings);
        this.playerAgentGridMapping = playerAgentGridMapping;
    }
    
    
    public GridMapping<PlayerAgent> getPlayerAgentGridMapping()
    {
        return playerAgentGridMapping;
    }
}
