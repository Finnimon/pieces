package com.gitgud.pieces.model.mission;

import com.gitgud.engine.control.action.Action;
import com.gitgud.engine.control.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.control.action.MissionMovementAction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;

import java.util.*;


public class Mission implements ActionAwaiterModel<GameObject>
{
    //todo render
    private final GridMap<GameObject> gridMap;
    
    
    //todo render on top of map at playerAgentPosition
    private final PlayerAgent playerAgent;
    
    
    private final FightAgent[] activeFightAgents;
    
    
    //todo render only in selction screen
    private final FightAgent[] discardedFightAgents;
    
    
    //todo render position for the playeragentsprite
    private Tile playerAgentPosition;
    
    
    private boolean finished = false;
    
    
    public Mission(GridMap<GameObject> gridMap, Tile startingPosition, FightAgent[] activeFightAgents)
    {
        this(gridMap, new PlayerAgent(), startingPosition, activeFightAgents, new FightAgent[activeFightAgents.length]);
    }
    
    
    public Mission(GridMap<GameObject> gridMap, PlayerAgent playerAgent, Tile playerAgentPosition,
                   FightAgent[] activeFightAgents, FightAgent[] discardedFightAgents)
    {
        this.gridMap = gridMap;
        this.playerAgent = playerAgent;
        this.playerAgentPosition = playerAgentPosition;
        this.activeFightAgents = activeFightAgents;
        this.discardedFightAgents = discardedFightAgents;
    }
    
    
    private void addAvailableMovementActions(HashSet<Action<MissionController>> actions)
    {
        Tile from = getPlayerAgentPosition();
        Collection<Tile> inMovementRangeTiles = getPlayerAgent().findPossibleMovementTargets(getGridMap(), from);
        
        for (Tile tile : inMovementRangeTiles)
        {
            actions.add(new MissionMovementAction(from, tile));
        }
    }
    
    
    public GridMap<GameObject> getGridMap()
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
    
    
    public boolean isFinished()
    {
        return this.finished;
    }
    
    
    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }
    
    
    public void returnFightAgentsToPool()
    {
        ArrayList<FightAgent> fightAgents = new ArrayList<>();
        for (int i = 0; i < activeFightAgents.length; i++)
        {
            fightAgents.add(activeFightAgents[i]);
            fightAgents.add(discardedFightAgents[i]);
        }
        
        HashMap<FightAgentType, HashSet<FightAgent>> baseCampStash = ActiveGameController.getInstance().get().getPlayer().army().baseCampStash();
        
        fightAgents.stream().filter(Objects::nonNull).forEach(fA -> baseCampStash.get(fA.getType()).add(fA));
    }
}
