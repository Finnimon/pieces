package com.gitgud.pieces.model.fight;

import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.EnemyAlgorithm;
import com.gitgud.pieces.model.game.ActiveGame;
import com.gitgud.pieces.model.game.GameState;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;


/**
 * The Object in which a Fight takes place
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @since  16.05.2024
 * @version  2.0
 */
public class Fight implements ActionAwaiterModel<FightAgent>
{
    //todo render
    private final GridMap<FightAgent> gridMap;
    
    
    //todo render at bottom of screen
    private final FightTimeLine fightTimeLine;
    
    
    //todo render next to timeline?
    private final SimpleIntegerProperty turn = new SimpleIntegerProperty(1);
    
    
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
    
    
    @Override
    public SimpleIntegerProperty turnProperty()
    {
        return turn;
    }
    
    
    public void incrementTurn()
    {
        getFightTimeLine().advance();
        ActionAwaiterModel.super.incrementTurn();
    }
    
    
    public FightTimeLine getFightTimeLine()
    {
        return fightTimeLine;
    }
    
    
    public void end()
    {
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        
        ArrayList<FightAgent> fightAgents = fightTimeLine.getAllAgents();
        fightAgents.removeIf(fa -> fa == null || fa.getAllegiance() == EnemyAlgorithm.ENEMY_ALLEGIANCE);
        
        if (ActiveGameController.getGameState() == GameState.MISSION_FIGHT)
        {
            ActiveGameController.getInstance().get().getMission().setFinished(fightAgents.isEmpty());
            FightAgent[] activeFightAgents = activeGame.getMission().getActiveFightAgents();
            fightAgents.toArray(activeFightAgents);
        }
    }
    
    
    public boolean isFinished()
    {
        
        ArrayList<FightAgent> survivingAgents = fightTimeLine.getAllAgents();
        
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
    
    
    public void fixTimeLine()
    {
        TreeSet<FightAgent> current = getFightTimeLine().current();
        
        Collection<FightAgent> nonNullElements = gridMap.nonNullElements();
        if (nonNullElements.containsAll(current))
        {
            return;
        }
        
        current.clear();
        fightTimeLine.next().clear();
        current.addAll(nonNullElements);
    }
}