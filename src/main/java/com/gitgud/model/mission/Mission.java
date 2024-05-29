package com.gitgud.model.mission;

import com.gitgud.model.gameObjects.gridMovable.PlayerAgent;
import com.gitgud.model.gameObjects.interactable.Interactable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;


public class Mission
{
    //todo render
    private final GridMap<Interactable> gridMap;


    //todo render on top of map at playerAgentPosition
    private final PlayerAgent playerAgent;

    //todo render position for the playeragentsprite
    private Tile playerAgentPosition;
    
    public void setPlayerAgentPosition(Tile playerAgentPosition)
    {
        this.playerAgentPosition = playerAgentPosition;
    }

    
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
