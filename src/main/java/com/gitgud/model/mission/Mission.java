package com.gitgud.model.mission;

import com.gitgud.model.gameObjects.gridMovable.PlayerAgent;
import com.gitgud.model.gameObjects.interactable.Interactable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;

import java.util.HashMap;


public class Mission
{
    private final GridMap<Interactable> gridMap;
    
    private final PlayerAgent playerAgent;
    
    
    public void setPlayerAgentPosition(Tile playerAgentPosition)
    {
        this.playerAgentPosition = playerAgentPosition;
    }
    
    
    private Tile playerAgentPosition;
    
    public Mission(GridMap<Interactable> gridMap, Tile startingPosition)
    {
        this.gridMap = gridMap;
        
        playerAgent=new PlayerAgent();
        playerAgentPosition = startingPosition;
    }
    
    
    public GridMap<Interactable> getGridMap()
    {
        return gridMap;
    }
    
    
    public PlayerAgent getPlayerAgent()
    {
        return playerAgent;
    }
    
    
    public Tile getPlayerAgentPosition()
    {
        return playerAgentPosition;
    }
}
