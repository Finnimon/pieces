package com.gitgud.pieces.model.fight;

import com.gitgud.engine.control.action.ActionAwaiterModel;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.EnemyAlgorithm;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;


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
    private final SimpleIntegerProperty turn = new SimpleIntegerProperty(0);
    
    
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
        
        ArrayList<FightAgent> fightAgents = getAllNonDeadAgents();
        fightAgents.removeIf(fa -> fa.getAllegiance() == EnemyAlgorithm.ENEMY_ALLEGIANCE);
        
        if (activeGame.getGameState() == GameState.MISSION_FIGHT)
        {
            FightAgent[] activeFightAgents = activeGame.getMission().getActiveFightAgents();
            fightAgents.toArray(activeFightAgents);
        }
    }
    
    
    public boolean isFinished()
    {
        
        ArrayList<FightAgent> survivingAgents = getAllNonDeadAgents();
        
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
    
    
    private ArrayList<FightAgent> getAllNonDeadAgents()
    {
        ArrayList<FightAgent> survivingAgents = new ArrayList<>(fightTimeLine.current());
        survivingAgents.addAll(fightTimeLine.next());
        return survivingAgents;
    }
}