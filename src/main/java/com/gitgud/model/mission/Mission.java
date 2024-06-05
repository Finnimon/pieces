package com.gitgud.model.mission;

import com.gitgud.model.Ending;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.gameObjects.gridMovable.PlayerAgent;
import com.gitgud.model.gameObjects.interactable.Interactable;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;


public class Mission implements Ending
{
    //todo render
    private final GridMap<Interactable> gridMap;
    
    
    //todo render on top of map at playerAgentPosition
    private final PlayerAgent playerAgent;
    
    
    //todo render position for the playeragentsprite
    private Tile playerAgentPosition;
    
    private final FightAgent[] activeFightAgents;
    
    //todo render only in selction screen
    private final FightAgent[] discardedFightAgents;
    
    
    private boolean finished = false;
    
    
    public Mission(GridMap<Interactable> gridMap, Tile startingPosition, FightAgent[] activeFightAgents)
    {
        this.gridMap = gridMap;
        this.activeFightAgents = activeFightAgents;
        this.discardedFightAgents = new FightAgent[activeFightAgents.length];
        
        playerAgent = new PlayerAgent();
        playerAgentPosition = startingPosition;
    }
    
    
    public Mission(GridMap<Interactable> gridMap, PlayerAgent playerAgent, Tile playerAgentPosition,
                   FightAgent[] activeFightAgents, FightAgent[] discardedFightAgents)
    {
        this.gridMap = gridMap;
        this.playerAgent = playerAgent;
        this.playerAgentPosition = playerAgentPosition;
        this.activeFightAgents = activeFightAgents;
        this.discardedFightAgents = discardedFightAgents;
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
    
    
    public void setPlayerAgentPosition(Tile playerAgentPosition)
    {
        this.playerAgentPosition = playerAgentPosition;
    }
    
    
    
    public FightAgent[] getActiveFightAgents()
    {
        return activeFightAgents;
    }
    
    
    public FightAgent[] getDiscardedFightAgents()
    {
        return discardedFightAgents;
    }
    
    @Override
    public void end()
    {
    
    }
    
    
    @Override
    public boolean isFinished()
    {
        return this.finished;
    }
    
    
    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }
    

}
