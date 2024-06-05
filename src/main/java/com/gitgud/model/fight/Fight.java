package com.gitgud.model.fight;

import com.gitgud.control.ActiveGameController;
import com.gitgud.model.Ending;
import com.gitgud.model.activeGame.ActiveGame;
import com.gitgud.model.activeGame.GameState;
import com.gitgud.model.gameObjects.FightAgentType;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * The Object in which a Fight or online Fight Takes place
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class Fight implements Ending
{
    //todo render
    private final GridMap<FightAgent> gridMap;
    
    
    private final HashMap<Player, HashSet<FightAgent>> ownershipMap;
    
    
    //todo render at bottom of screen
    private final FightTimeLine fightTimeLine;
    
    
    //todo render next to timeline?
    private int turn = 0;
    
    
    public Fight(GridMap<FightAgent> gridMap, HashMap<Player, HashSet<FightAgent>> ownershipMap,
                 FightTimeLine fightTimeLine)
    {
        this.gridMap = gridMap;
        this.ownershipMap = ownershipMap;
        this.fightTimeLine = fightTimeLine;
    }
    
    
    public GridMap<FightAgent> getGridMap()
    {
        return gridMap;
    }
    
    
    public HashMap<Player, HashSet<FightAgent>> getOwnershipMap()
    {
        return ownershipMap;
    }
    
    
    public int getTurn()
    {
        return turn;
    }
    
    
    public int increaseTurn()
    {
        return ++turn;
    }
    
    
    public FightTimeLine getFightTimeLine()
    {
        return fightTimeLine;
    }
    
    
    @Override
    public void end()
    {
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        FightAgent[] survivingAgents = (FightAgent[]) ownershipMap.get(activeGame.getPlayer()).stream().filter(
                x -> !x.isDead()).toArray();
        if (activeGame.getGameState() == GameState.MISSION_FIGHT)
        {
            FightAgent[] activeFightAgents = activeGame.getMission().getActiveFightAgents();
            System.arraycopy(survivingAgents, 0, activeFightAgents, 0, survivingAgents.length);
        }
        else
        {
            HashMap<FightAgentType, ArrayList<FightAgent>> baseCampStash = activeGame.getPlayer().army().baseCampStash();
            for (FightAgent fightAgent : survivingAgents)
            {
                baseCampStash.get(fightAgent.getType()).add(fightAgent);
            }
        }
        
        
        activeGame.setFight(null);
    }
    
    
    @Override
    public boolean isFinished()
    {

        for (Player player : ownershipMap.keySet())
        {
            boolean anyAlive = false;
            
            for (FightAgent fightAgent: ownershipMap.get(player))
            {
                anyAlive = anyAlive ||!fightAgent.isDead();
            }
            
            if (!anyAlive)
            {
                return true;
            }
        }
        
        return false;
        
    }
}