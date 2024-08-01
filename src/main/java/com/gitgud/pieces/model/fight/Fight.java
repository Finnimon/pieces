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
 * @version 2.0
 * @Owner: Finn L.
 * @since 16.05.2024
 */
public class Fight implements ActionAwaiterModel<FightAgent>
{
    /**
     * The GridMap on which the fight takes place.
     */
    private final GridMap<FightAgent> gridMap;
    
    
    /**
     * The timeline of the fight.
     */
    private final FightTimeLine fightTimeLine;
    
    
    /**
     * The current turn.
     */
    private final SimpleIntegerProperty turn = new SimpleIntegerProperty(1);
    
    
    /**
     * Constructs a Fight on the given GridMap.
     * @param gridMap The GridMap.
     */
    public Fight(GridMap<FightAgent> gridMap)
    {
        this(gridMap, FightTimeLine.create(gridMap.nonNullElements()));
    }
    
    
    /**
     * Sets the given GridMap and FightTimeLine.
     * @param gridMap The GridMap.
     * @param fightTimeLine The FightTimeLine.
     */
    public Fight(GridMap<FightAgent> gridMap, FightTimeLine fightTimeLine)
    {
        this.gridMap = gridMap;
        this.fightTimeLine = fightTimeLine;
    }
    
    
    /**
     * Getter for the fights GridMap.
     * @return The fights GridMap.
     */
    public GridMap<FightAgent> getGridMap()
    {
        return gridMap;
    }
    
    
    @Override
    public SimpleIntegerProperty turnProperty()
    {
        return turn;
    }
    
    
    /**
     * Increments the fights turn.
     */
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