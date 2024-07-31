package com.gitgud.pieces.control;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.actionChoice.*;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.control.game.Game;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


/**
 * {@link ActionAwaitingController} for {@link Fight}s.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see Fight
 * @since 20.06.2024
 */
public class FightController extends ActionAwaitingController<Fight, FightAgent, FightRender>
{
    /**
     * The index of the root movement choice in the first child layer of the action choice tree.
     */
    public static final int MOVEMENT_CHOICE_INDEX = 0;
    
    
    /**
     * The index of the root attack choice in the first child layer of the action choice tree.
     */
    public static final int ATTACK_CHOICE_INDEX = 1;
    
    
    /**
     * The index of the root spell choice in the first child layer of the action choice tree.
     */
    public static final int SPELL_CHOICE_INDEX = 2;
    
    
    /**
     * The index of the skip turn choice in the first child layer of the action choice tree.
     */
    public static final int SKIP_TURN_CHOICE_INDEX = 3;
    
    
    /**
     * Constructs the fight controller with the given {@link Fight} and instantiates its {@link FightRender} from the
     * give {@code fight}.
     *
     * @param fight The {@link Fight} to be controlled.
     */
    public FightController(Fight fight)
    {
        super(fight, new FightRender(fight));
    }
    
    
    @Override
    public void advance()
    {
        Fight fight = this.getModel();
        fight.incrementTurn();
        if (tryEnd())
        {
            return;
        }
        FightRender render = getRender();
        render.updateRender();
        render.getGridMapRender().clearHighLights();
        hightlightActivePosition();
        executeActionChoiceTask(onSucceeded());
    }
    
    
    @Override
    public @NotNull Tile getActivePosition()
    {
        Fight fight = this.getModel();
        FightAgent activeFightAgent = getActiveFightAgent();
        
        return fight.getGridMap().getVertex(activeFightAgent);
    }
    
    
    @Override
    public @NotNull ActionChoice<FightController, Fight, FightAgent, FightRender> getActionChoice()
    {
        List<ActionChoice<FightController, Fight, FightAgent, FightRender>> choices = new ArrayList<>();
        
        choices.add(MOVEMENT_CHOICE_INDEX, getMovementChoiceRoot());
        choices.add(ATTACK_CHOICE_INDEX, getAttackRootChoice());
        choices.add(SPELL_CHOICE_INDEX, getSpellRootChoice());
        choices.add(SKIP_TURN_CHOICE_INDEX, getSkipTurnChoice());
        return new RootActionChoice<>("root", "root", this, choices);//todo
    }
    
    
    @Override
    public void start()
    {
        getModel().fixTimeLine();
        getRender().show();
        executeActionChoiceTask(onSucceeded());
    }
    
    
    /**
     * <p>Gets the current root movement choice.
     * <p>Do not call on the JavaFX Application Thread
     *
     * @return The current root movement choice.
     * @see MovementRootChoice
     */
    private RootToActionChoice<FightController, Fight, FightAgent, FightRender> getMovementChoiceRoot()
    {
        Tile position = getActivePosition();
        
        FightAgent activeFightAgent = getActiveFightAgent();
        
        return new MovementRootChoice<>(this, activeFightAgent, position);
    }
    
    
    /**
     * <p>Gets the current root attack choice.
     *
     * @return The current root attack choice.
     * @see AttackRootChoice#AttackRootChoice(ActionAwaitingController, com.gitgud.engine.model.gameobjects.agent.Fighter)
     */
    public AttackRootChoice<FightController, Fight, FightAgent, FightRender> getAttackRootChoice()
    {
        FightAgent activeFightAgent = getActiveFightAgent();
        
        return new AttackRootChoice<>(this, activeFightAgent);//todo
    }
    
    
    /**
     * Gets the current root spell choice.
     *
     * @return The current root spell choice.
     */
    private RootToActionChoice<FightController, Fight, FightAgent, FightRender> getSpellRootChoice()
    {
        FightAgent agent = getActiveFightAgent();
        if (!(agent instanceof SpellCasterFightAgent spellCasterFightAgent))
        {
            return new RootToActionChoice<>("Spells", "Cast Spells, available to you", this, new ArrayList<>());
        }
        
        throw new RuntimeException("not implemented");
    }
    
    
    /**
     * Creates a consumer with the following logic:
     * <p>The consumer for {@link #executeActionChoiceTask(Consumer)}.
     * <p>Offers the choice to either the player or an {@link EnemyAlgorithm} depending on the active
     * {@link FightAgent}'s {@link com.gitgud.pieces.model.fight.Allegiance}.
     *
     * @return The consumer for {@link #executeActionChoiceTask(Consumer)}.
     */
    private @NotNull Consumer<ActionChoice> onSucceeded()
    {
        return actionChoice ->
        {
            if (getActiveFightAgent().getAllegiance() == EnemyAlgorithm.ENEMY_ALLEGIANCE)
            {
                new EnemyAlgorithm(this).act((RootChoice) actionChoice);
                return;
            }
            hightlightActivePosition();
            actionChoice.show(this);
        };
    }
    
    
    /**
     * Gets the currently active {@link FightAgent} from the {@link Fight}'s
     * {@link com.gitgud.pieces.model.fight.FightTimeLine}.
     *
     * @return The currently active {@link FightAgent}.
     */
    private FightAgent getActiveFightAgent()
    {
        return getModel().getFightTimeLine().getActiveFightAgent();
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
    
    
    @Override
    public void end()
    {
        getModel().end();
        
        ActiveGameController.getInstance().get().setFight(null);
        
        Game.Flow.showNextScene();
    }
}
