package com.gitgud.pieces.control;


import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootChoice;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.FightMovementChoice;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.gitgud.pieces.control.FightController.*;


public class EnemyAlgorithm
{
    private final FightController fightController;
    
    
    private final Allegiance enemyAllegiance = Allegiance.WHITE;
    
    
    public EnemyAlgorithm(FightController fightController)
    {
        this.fightController = fightController;
    }
    
    
    public static int randomInt(int min, int max)
    {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    
    
    public void act(RootChoice actionChoice)
    {
        //        Task<Boolean> waitTask = new Task<>()
        //        {
        //            @Override
        //            protected Boolean call() throws Exception
        //            {
        //                wait(100);
        //                return true;
        //            }
        //        };
        //        waitTask.setOnSucceeded(x -> selectActionChoice(actionChoice));
        //        ExecutorService exec = Executors.newSingleThreadExecutor();
        //        exec.execute(waitTask);
        //        exec.shutdown();
        ActionChoice choice = choose(actionChoice);
        select(choice);
    }
    
    
    private ActionChoice choose(RootChoice rootChoice)
    {
        List<ActionChoice<FightController, Fight, FightAgent, FightRender>> actionChoices = rootChoice.getChoices();
        
        MovementRootChoice movementChoices = (MovementRootChoice) actionChoices.get(MOVEMENT_CHOICE_INDEX);
        RootChoice attackChoices = (RootChoice) actionChoices.get(ATTACK_CHOICE_INDEX);
        RootChoice spellChoices = (RootChoice) actionChoices.get(SPELL_CHOICE_INDEX);
        ActionChoice<FightController, Fight, FightAgent, FightRender> skipTurnChoice = actionChoices.get(
                SKIP_TURN_CHOICE_INDEX);
        
        ActionChoice choice = null;
        
        if (!attackChoices.isEmpty()) return chooseRandomChoice(attackChoices.getChoices());
        
        if (!movementChoices.isEmpty()) return determineBestMovementChoice(movementChoices.getChoices());
        
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
        
        return chooseRandomChoice(rootChoice.getChoices());
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
    
    
    private void selectFightMovementChoice(FightMovementChoice actionChoice)
    {
        actionChoice.getAction().enAct(fightController);
        fightController.getRender().getHud().clearChoices();
        RootChoice attackRootChoice = fightController.getAttackRootChoice();
        if (attackRootChoice.isEmpty())
        {
            fightController.advance();
            return;
        }
        select(chooseRandomChoice(attackRootChoice.getChoices()));
        fightController.advance();
    }
    
    
    public Allegiance getEnemyAllegiance()
    {
        return enemyAllegiance;
    }
    
    
    private FightMovementChoice determineBestMovementChoice(List<FightMovementChoice> choices)
    {
        GridMap<FightAgent> gridMap = fightController.getModel().getGridMap();
        
        Tile from = choices.getFirst().getAction().getFrom();
        FightAgent agent = gridMap.get(from);
        
        HashMap<Tile, FightMovementChoice> to = new HashMap<>();
        
        choices.stream().forEach(x -> to.put(x.getAction().getTo(), x));
        
        Set<Tile> allTiles = gridMap.verticeSet();
        
        allTiles.remove(from);
        allTiles.removeAll(to.keySet());
        
        double currentShortestDistance = Double.MAX_VALUE;
        FightMovementChoice bestChoice = null;
        
        if (agent==null) System.out.println(from);
        for (Tile tile : allTiles)
        {
            FightAgent otherAgent = gridMap.get(tile);
            if (otherAgent == null || agent.getAllegiance() == otherAgent.getAllegiance()) continue;
            
            for (Tile toTile : to.keySet())
            {
                double distance = toTile.distance(tile);
                if (distance > currentShortestDistance) continue;
                bestChoice = to.get(toTile);
                currentShortestDistance = distance;
            }
        }
        
        return bestChoice;
    }
}
