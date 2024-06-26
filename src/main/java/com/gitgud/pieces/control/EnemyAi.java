package com.gitgud.pieces.control;


import com.gitgud.engine.control.actionChoice.*;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.view.render.fight.FightRender;

import java.util.List;


public class EnemyAi
{
    private final FightController fightController;
    private final Allegiance enemyAllegiance = Allegiance.WHITE;
    
    public EnemyAi(FightController fightController)
    {
        this.fightController = fightController;
    }
    
    
    public static int randomInt(int min, int max)
    {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    
    
    public void act()
    {
        ActionChoice<FightController, Fight, FightAgent, FightRender> actionChoice = fightController.getActionChoice();
        
        if (!(actionChoice instanceof RootActionChoice<FightController, Fight, FightAgent, FightRender> rootActionChoice))
        {
            actionChoice.select();
            return;
        }
        
        List<ActionChoice<FightController, Fight, FightAgent, FightRender>> actionChoices = rootActionChoice.getChoices();
        
        RootToActionChoice<FightController, Fight, FightAgent, FightRender> movementChoices = (RootToActionChoice<FightController, Fight, FightAgent, FightRender>) actionChoices.get(
                0);
        AttackRootChoice<FightController, Fight, FightAgent, FightRender> attackChoices = (AttackRootChoice<FightController, Fight, FightAgent, FightRender>) actionChoices.get(
                1);
        RootToActionChoice<FightController, Fight, FightAgent, FightRender> spellChoices = (RootToActionChoice<FightController, Fight, FightAgent, FightRender>) actionChoices.get(
                2);
        ActionChoice<FightController, Fight, FightAgent, FightRender> skipTurnChoice = actionChoices.get(3);
        
        
        if (tryRootChoice(attackChoices))
        {
            return;
        }
        
        if (tryRootChoice(movementChoices))
        {
            return;
        }
        
        
        skipTurnChoice.select();
    }
    
    
    private boolean tryRootChoice(RootChoice rootChoice)
    {
        if (rootChoice.isEmpty())
        {
            return false;
        }
        
        List<ActionChoice<?,?,?,?>> choices = rootChoice.getChoices();
        
        selectRandomChoice(choices);
        
        return true;
    }
    
    
    private void selectRandomChoice(List<ActionChoice<?,?,?,?>> choices)
    {
        int index = randomInt(0, choices.size() - 1);
        
        ActionChoice<?,?,?,?> actionChoice= choices.get(index);
        actionChoice.select();
        
        if (!(actionChoice instanceof RootChoice rootChoice))
        {
            actionChoice.select();
            return;
        }
        
        selectRandomChoice(rootChoice.getChoices());
    }
    
    
    public Allegiance getEnemyAllegiance()
    {
        return enemyAllegiance;
    }
    
    private boolean selectBestMovementChoice()
    {
        return false;
    }
}
