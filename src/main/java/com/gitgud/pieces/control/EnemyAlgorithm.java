package com.gitgud.pieces.control;


import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootChoice;
import com.gitgud.engine.control.actionChoice.ToActionChoice;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.FightMovementChoice;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;
import javafx.beans.property.IntegerProperty;
import javafx.concurrent.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.gitgud.pieces.control.FightController.*;


public class EnemyAlgorithm
{
    public static final Allegiance ENEMY_ALLEGIANCE = Allegiance.WHITE;
    
    
    private final FightController fightController;
    
    
    public EnemyAlgorithm(FightController fightController)
    {
        this.fightController = fightController;
    }
    
    
    public static int randomInt(int min, int max)
    {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    
    
    public synchronized void act(RootChoice actionChoice)
    {
        fightController.getRender().getHud().clearChoices();
        Task<ActionChoice> task = chooseTask(actionChoice);
        task.setOnSucceeded(x -> select(task.getValue()));
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.schedule(task, 500, TimeUnit.MILLISECONDS);
        exec.shutdown();
    }
    
    
    Task<ActionChoice> chooseTask(RootChoice rootChoice)
    {
        return new Task<>()
        {
            @Override
            protected ActionChoice call() throws InterruptedException
            {
                return choose(rootChoice);
            }
        };
    }
    
    
    private ActionChoice choose(RootChoice rootChoice)
    {
        List<ActionChoice<FightController, Fight, FightAgent, FightRender>> actionChoices = rootChoice.getChildren();
        
        MovementRootChoice movementChoices = (MovementRootChoice) actionChoices.get(MOVEMENT_CHOICE_INDEX);
        RootChoice attackChoices = (RootChoice) actionChoices.get(ATTACK_CHOICE_INDEX);
        RootChoice spellChoices = (RootChoice) actionChoices.get(SPELL_CHOICE_INDEX);
        ActionChoice<FightController, Fight, FightAgent, FightRender> skipTurnChoice = actionChoices.get(
                SKIP_TURN_CHOICE_INDEX);
        
        ActionChoice choice = null;
        
        if (!attackChoices.isEmpty())
        {
            return chooseRandomChoice(attackChoices.getChildren());
        }
        
        if (!movementChoices.isEmpty())
        {
            return determineBestMovementChoice(movementChoices.getChildren());
        }
        
        return skipTurnChoice;
    }
    
    
    private ActionChoice chooseRandomChoice(List<ActionChoice<?, ?, ?, ?>> choices)
    {
        int index = randomInt(0, choices.size() - 1);
        
        ActionChoice<?, ?, ?, ?> actionChoice = choices.get(index);
        
        if (!(actionChoice instanceof RootChoice rootChoice))
        {
            return actionChoice;
        }
        return chooseRandomChoice(rootChoice.getChildren());
    }
    
    
    private void select(ActionChoice actionChoice)
    {
        if (actionChoice instanceof FightMovementChoice fightMovementChoice)
        {
            selectFightMovementChoice(fightMovementChoice);
            return;
        }
        actionChoice.select();
    }
    
    
    private synchronized void selectFightMovementChoice(FightMovementChoice actionChoice)
    {
        IntegerProperty turnProperty = fightController.getModel().turnProperty();
        int turn = turnProperty.getValue();
        
        actionChoice.select();
        if (turn != turnProperty.getValue())
        {
            return;
        }
        
        fightController.getRender().getHud().clearChoices();
        
        RootChoice<ToActionChoice<FightController, Fight, FightAgent, FightRender>> attackRootChoice =
                fightController.getAttackRootChoice();
        if (attackRootChoice.isEmpty())
        {
            return;
        }
        attackRootChoice.getChildren().get(0).select();
    }
    
    
    public Allegiance getEnemyAllegiance()
    {
        return ENEMY_ALLEGIANCE;
    }
    
    
    private FightMovementChoice determineBestMovementChoice(List<FightMovementChoice> choices)
    {
        GridMap<FightAgent> gridMap = fightController.getModel().getGridMap();
        
        Tile from = choices.getFirst().getAction().getFrom();
        FightAgent agent = gridMap.get(from);
        
        HashMap<Tile, FightMovementChoice> to = new HashMap<>();
        
        choices.forEach(x -> to.put(x.getAction().getTo(), x));
        
        Set<Tile> allTiles = gridMap.verticeSet();
        
        allTiles.remove(from);
        allTiles.removeAll(to.keySet());
        
        double currentShortestDistance = Double.MAX_VALUE;
        FightMovementChoice bestChoice = null;
        
        for (Tile tile : allTiles)
        {
            FightAgent otherAgent = gridMap.get(tile);
            if (otherAgent == null || agent.getAllegiance() == otherAgent.getAllegiance())
            {
                continue;
            }
            
            for (Tile toTile : to.keySet())
            {
                double distance = toTile.distance(tile);
                if (distance > currentShortestDistance)
                {
                    continue;
                }
                bestChoice = to.get(toTile);
                currentShortestDistance = distance;
            }
        }
        
        return bestChoice;
    }
}
