package com.gitgud.pieces.control.actionChoices;

import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.control.actionChoice.ToActionChoice;
import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.FightController;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;


public class FightMovementChoice extends ToActionChoice<FightController, Fight, FightAgent, FightRender>
{
    public FightMovementChoice(String name, String description, FightController awaiter,
                               ToAction<FightController, Tile> action)
    {
        super("Move", "Move the active character to a new position.", awaiter, action);
    }
    
    
    @Override
    public void select()
    {
        FightController fightController = (FightController) getAwaiter();
        FightRender fightRender= fightController.getRender();
        fightRender.getHud().clearChoices();
        fightRender.getGridMapRender().clearHighLights();
        
        ToAction<FightController, Tile> action = getAction();
        
        action.enAct(fightController);
        
        RootToActionChoice<FightController, Fight, FightAgent, FightRender> attackRootChoice = fightController.getAttackRootChoice();
        
        if (attackRootChoice.isEmpty())
        {
            fightController.advance();
            return;
        }
        
        fightController.getRender().getHud().clearChoices();
        
        attackRootChoice.show(fightController);
    }
}
