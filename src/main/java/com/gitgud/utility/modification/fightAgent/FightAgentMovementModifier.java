package com.gitgud.utility.modification.fightAgent;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.modification.Modifier;


public class FightAgentMovementModifier extends Modifier<FightAgent>
{
    private final int movementRangeModifier;
    private final int initiativeModifier;
    
    public FightAgentMovementModifier(int movementRangeModifier, int initiativeModifier)
    {
        this.movementRangeModifier = movementRangeModifier;
        this.initiativeModifier = initiativeModifier;
    }
    
    public FightAgent modify(FightAgent fightAgent)
    {
        int movementRange = fightAgent.getMovementRange() + movementRangeModifier;
        int initiative = fightAgent.getInitiative() + initiativeModifier;
        
        fightAgent.setMovementRange(movementRange);
        fightAgent.setInitiative(initiative);
        
        return fightAgent;
    }
    
    public FightAgent demodify(FightAgent fightAgent)
    {
        int movementRange = fightAgent.getMovementRange() - movementRangeModifier;
        int initiative = fightAgent.getInitiative() - initiativeModifier;
        
        fightAgent.setMovementRange(movementRange);
        fightAgent.setInitiative(initiative);
        
        return fightAgent;
    }
    
}
