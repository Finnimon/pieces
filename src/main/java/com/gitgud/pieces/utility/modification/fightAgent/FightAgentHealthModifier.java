package com.gitgud.pieces.utility.modification.fightAgent;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public class FightAgentHealthModifier extends Modifier<FightAgent>
{
    private final int healthModifier;
    
    
    private final int maxHealthModifier;
    
    
    public FightAgentHealthModifier(int healthModifier, int maxHealthModifier, int healthModifier1,
                                    int maxHealthModifier1)
    {
        this.healthModifier = healthModifier1;
        this.maxHealthModifier = maxHealthModifier1;
    }
    
    
    @Override
    public FightAgent modify(FightAgent fightAgent)
    {
        fightAgent.setHealth(fightAgent.getHealth() + healthModifier);
        fightAgent.setMaxHealth(fightAgent.getMaxHealth() + maxHealthModifier);
        return fightAgent;
    }
    
    
    @Override
    public FightAgent demodify(FightAgent fightAgent)
    {
        fightAgent.setHealth(fightAgent.getHealth() - healthModifier);
        fightAgent.setMaxHealth(fightAgent.getMaxHealth() - maxHealthModifier);
        return fightAgent;
    }
}
