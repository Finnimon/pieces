package com.gitgud.pieces.control.actionChoices;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.action.TileMovementAction;
import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.control.actionChoice.ToActionChoice;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.FightController;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;
import org.jetbrains.annotations.NotNull;


/**
 * Covers special case, that {@link FightAgent} may move and attack afterward within the same turn.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class FightMovementChoice extends ToActionChoice<FightController, Fight, FightAgent, FightRender>
{
    
    
    public static final String NAME = "Move";
    
    
    public static final String DESCRIPTION = "Move the active character to a new position.";
    
    
    /**
     * Calls super constructor.
     *
     * @param awaiter The {@link FightController} this RootChoice will be enacted upon.
     * @param action  The {@link TileMovementAction} that will be enacted upon selection.
     * @see ToActionChoice#ToActionChoice(String, String, ActionAwaitingController, ToAction)
     */
    public FightMovementChoice(FightController awaiter, ToAction<FightController, Tile> action)
    {
        super(NAME, DESCRIPTION, awaiter, action);
    }
    
    
    @Override
    public void select()
    {
        FightController fightController = getAwaiter();
        FightRender fightRender = fightController.getRender();
        fightRender.getHud().clearChoices();
        fightRender.getGridMapRender().clearHighLights();
        
        ToAction<FightController, Tile> action = getAction();
        
        action.enAct(fightController);
        
        RootToActionChoice<FightController, Fight, FightAgent, FightRender> attackRootChoice =
                fightController.getAttackRootChoice();
        // if no attack choice directly after movement advances the FightController
        if (attackRootChoice.isEmpty())
        {
            fightController.advance();
            return;
        }
        
        attackRootChoice.select();
    }
    
    
    @Override
    public @NotNull TileMovementAction<FightController, Fight, FightAgent, FightRender> getAction()
    {
        return (TileMovementAction<FightController, Fight, FightAgent, FightRender>) super.getAction();
    }
}
