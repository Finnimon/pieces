package com.gitgud.pieces.control;


import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootChoice;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.FightMovementChoice;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;
import javafx.concurrent.Task;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.gitgud.pieces.control.FightController.*;


/**
 * Algorithm for choosing an {@link ActionChoice} for the enemy during a {@link Fight}.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see Fight
 * @see FightController
 * @since 20.06.2024
 */
public class EnemyAlgorithm
{
    /**
     * The allegiance of the enemy in story mode.
     */
    public static final Allegiance ENEMY_ALLEGIANCE = Allegiance.WHITE;
    
    
    /**
     * Delay so that the player can understand and see what the enemy is doing.
     */
    public static final int ACT_DELAY_MILLIS = 500;
    
    
    /**
     * The controller of the {@link Fight} this enemy is part of.
     */
    private final FightController fightController;
    
    
    /**
     * Constructor that associates a FightController with this EnemyAlgorithm.
     *
     * @param fightController the controller of the {@link Fight} this EnemyAlgorithm is part of.
     */
    public EnemyAlgorithm(@NotNull FightController fightController)
    {
        this.fightController = fightController;
    }
    
    
    /**
     * Returns a random integer between min (inclusive) and max (inclusive).
     *
     * @param min The inclusive lower bound of the random number returned.
     * @param max The inclusive upper bound of the random number returned.
     * @return a random integer between min (inclusive) and max (inclusive)
     */
    private static int randomInt(int min, int max)
    {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    
    
    /**
     * Actually chooses an {@link ActionChoice} for the enemy.
     *
     * @param actionChoice The {@link RootChoice} to choose from.
     */
    public void act(@NotNull RootChoice actionChoice)
    {
        //asserts that the player does not choose for the enemyalgorithm
        fightController.getRender().getHud().clearChoices();
        
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.schedule(chooseTask(actionChoice), ACT_DELAY_MILLIS, TimeUnit.MILLISECONDS);
        exec.shutdown();
    }
    
    
    /**
     * Creates a task that will choose an ActionChoice on the worker thread and select it on the main thread.
     *
     * @param rootChoice The {@link RootChoice} to choose from.
     * @return A task that will choose an ActionChoice on the worker thread and select it on the main thread.
     * @Precondition: The {@link RootChoice} cannot be empty.
     * @Postcondition: The task will choose an ActionChoice on the worker thread and select it on the main thread.
     */
    @NotNull
    private Task<ActionChoice> chooseTask(@NotNull RootChoice rootChoice)
    {
        return new Task<>()
        {
            @Override
            protected ActionChoice call() throws InterruptedException
            {
                return choose(rootChoice);
            }
            
            
            @Override
            protected void succeeded()
            {
                select(getValue());
            }
        };
    }
    
    
    /**
     * Chooses the best {@link ActionChoice} for the enemy.
     *
     * @param rootChoice The {@link RootChoice} to choose from.
     * @return The best {@link ActionChoice} for the enemy.
     */
    private ActionChoice choose(RootChoice rootChoice)
    {
        List<ActionChoice<FightController, Fight, FightAgent, FightRender>> actionChoices = rootChoice.getChildren();
        
        var movementChoices = (MovementRootChoice) actionChoices.get(MOVEMENT_CHOICE_INDEX);
        var attackChoices = (RootChoice) actionChoices.get(ATTACK_CHOICE_INDEX);
        var spellChoices = (RootChoice) actionChoices.get(SPELL_CHOICE_INDEX);
        var skipTurnChoice = actionChoices.get(SKIP_TURN_CHOICE_INDEX);
        
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
    
    
    /**
     * <p>Chooses a random {@link ActionChoice} from a list of {@link ActionChoice}s.
     * <p>If the chosen {@link ActionChoice} is a {@link RootChoice}, it will recursively choose a random
     * {@link ActionChoice} from its children.
     *
     * @param choices The list of {@link ActionChoice}s to choose from.
     * @return The random {@link ActionChoice} from the list.
     */
    private ActionChoice chooseRandomChoice(List<ActionChoice<?, ?, ?, ?>> choices)
    {
        int index = randomInt(0, choices.size() - 1);
        
        var actionChoice = choices.get(index);
        
        if (!(actionChoice instanceof RootChoice rootChoice))
        {
            return actionChoice;
        }
        return chooseRandomChoice(rootChoice.getChildren());
    }
    
    
    /**
     * <p> Selection method for {@link ActionChoice}s.
     * <p> If the {@link ActionChoice} is a {@link FightMovementChoice}, it will try to attack after moving, if the
     * turn has not yet ended.
     *
     * @param actionChoice The {@link ActionChoice} to select.
     * @see FightMovementChoice
     * @see ActionChoice#select()
     * @see #selectFightMovementChoice(FightMovementChoice)
     */
    private void select(@NotNull ActionChoice actionChoice)
    {
        if (actionChoice instanceof FightMovementChoice fightMovementChoice)
        {
            selectFightMovementChoice(fightMovementChoice);
            return;
        }
        actionChoice.select();
    }
    
    
    /**
     * Selects the {@link FightMovementChoice} and if the turn has not yet ended, it will try to attack.
     *
     * @param actionChoice The {@link FightMovementChoice} to select.
     */
    private synchronized void selectFightMovementChoice(@NotNull FightMovementChoice actionChoice)
    {
        var turnProperty = fightController.getModel().turnProperty();
        var turn = turnProperty.getValue();
        
        actionChoice.select();
        if (!Objects.equals(turn, turnProperty.getValue()))
        {
            return;
        }
        
        fightController.getRender().getHud().clearChoices();
        
        var attackRootChoice = fightController.getAttackRootChoice();
        if (attackRootChoice.isEmpty())
        {
            return;
        }
        attackRootChoice.getChildren().get(0).select();
    }
    
    
    /**
     * <p>Determines the best {@link FightMovementChoice} from a list of {@link FightMovementChoice}s.
     * <p>Tries to move closest to the player's FightAgents.
     * <p>This is a high-cost algorithm: Do not use it on the JavaFX Application Thread.
     *
     * @param choices The list of {@link FightMovementChoice}s to choose from.
     * @return The best {@link FightMovementChoice} from the list according to the algorithm.
     * @Precondition: The list of {@link FightMovementChoice}s cannot be empty.
     * @Postcondition: A {@link FightMovementChoice} will be returned.
     */
    private @NotNull FightMovementChoice determineBestMovementChoice(@NotNull List<FightMovementChoice> choices)
    {
        var gridMap = fightController.getModel().getGridMap();
        
        var from = choices.getFirst().getAction().getFrom();
        var agent = gridMap.get(from);
        
        HashMap<Tile, FightMovementChoice> to = new HashMap<>();
        
        choices.forEach(x -> to.put(x.getAction().getTo(), x));
        
        var allTiles = gridMap.verticeSet();
        
        allTiles.remove(from);
        allTiles.removeAll(to.keySet());
        
        var currentShortestDistance = Double.MAX_VALUE;
        FightMovementChoice bestChoice = null;
        
        for (var tile : allTiles)
        {
            var otherAgent = gridMap.get(tile);
            if (otherAgent == null || agent.getAllegiance() == otherAgent.getAllegiance())
            {
                continue;
            }
            
            for (var toTile : to.keySet())
            {
                var distance = toTile.distance(tile);
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
