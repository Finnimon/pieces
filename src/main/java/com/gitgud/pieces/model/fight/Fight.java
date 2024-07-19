package com.gitgud.pieces.model.fight;

import com.gitgud.engine.control.action.ActionAwaiterModel;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;


/**
 * The Object in which a Fight or online Fight Takes place
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class Fight implements ActionAwaiterModel<FightAgent>
{
    //todo render
    private final GridMap<FightAgent> gridMap;
    
    
    //todo render at bottom of screen
    private final FightTimeLine fightTimeLine;
    
    
    //todo render next to timeline?
    private SimpleIntegerProperty turn = new SimpleIntegerProperty(0);
    
    
    public Fight(GridMap<FightAgent> gridMap, FightTimeLine fightTimeLine)
    {
        this.gridMap = gridMap;
        this.fightTimeLine = fightTimeLine;
    }
    
    
    public Fight(GridMap<FightAgent> gridMap)
    {
        this(gridMap, FightTimeLine.create(gridMap.nonNullElements()));
    }
    
    
    public GridMap<FightAgent> getGridMap()
    {
        return gridMap;
    }
    
    
    public SimpleIntegerProperty getTurnProperty()
    {
        return turn;
    }
    
    
    public int incrementTurn()
    {
        getFightTimeLine().advance();
        return turn.add(1).get();
    }
    
    
    public FightTimeLine getFightTimeLine()
    {
        return fightTimeLine;
    }
    
    
    public void end()
    {
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        
        FightAgent[] survivingAgents;
        TreeSet<FightAgent> survivingAgentsTreeSet = fightTimeLine.current();
        survivingAgentsTreeSet.addAll(fightTimeLine.next());
        
        survivingAgents = survivingAgentsTreeSet.stream().filter(
                fightAgent -> !fightAgent.isDead() && fightAgent.getAllegiance() == Allegiance.BLACK).toArray(
                FightAgent[]::new);
        
        if (activeGame.getGameState() == GameState.MISSION_FIGHT)
        {
            FightAgent[] activeFightAgents = activeGame.getMission().getActiveFightAgents();
            System.arraycopy(survivingAgents, 0, activeFightAgents, 0, survivingAgents.length);
            return;
        }
        
        
        HashMap<FightAgentType, HashSet<FightAgent>> baseCampStash = activeGame.getPlayer().army().baseCampStash();
        
        for (FightAgent fightAgent : survivingAgents)
        {
            baseCampStash.get(fightAgent.getType()).add(fightAgent);
        }
        
    }
    
    
    public boolean isFinished()
    {
        
        ArrayList<FightAgent> survivingAgents = new ArrayList<>(fightTimeLine.current());
        survivingAgents.addAll(fightTimeLine.next());
        
        survivingAgents.stream().filter(x -> !x.isDead()).findFirst();
        
        Allegiance allegiance = survivingAgents.get(0).getAllegiance();
        
        for (FightAgent fightAgent : survivingAgents)
        {
            if (fightAgent.getAllegiance() != allegiance)
            {
                return false;
            }
        }
        
        return true;
        
    }
}