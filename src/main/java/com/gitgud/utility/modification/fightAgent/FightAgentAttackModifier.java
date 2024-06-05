package com.gitgud.utility.modification.fightAgent;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.modification.Modifier;


public class FightAgentAttackModifier extends Modifier<FightAgent>
{
    private final int meleeDamageModifier;
    
    
    private final int rangedDamageModifier;
    
    
    private final float accuracyModifier;
    
    
    public FightAgentAttackModifier(int meleeDamageModifier, int rangedDamageModifier, float accuracyModifier)
    {
        this.meleeDamageModifier = meleeDamageModifier;
        this.rangedDamageModifier = rangedDamageModifier;
        this.accuracyModifier = accuracyModifier;
    }
    
    
    public FightAgent modify(FightAgent fightAgent)
    {
        int meleeDamage = fightAgent.getMeleeDamage() + meleeDamageModifier;
        float accuracy = fightAgent.getAccuracy() * accuracyModifier;
        
        fightAgent.setMeleeDamage(meleeDamage);
        fightAgent.setAccuracy(accuracy);
        
        if (!fightAgent.isRangedAttacker())
        {
            return fightAgent;
        }
        
        int rangedDamage = fightAgent.getRangedDamage() + rangedDamageModifier;
        fightAgent.setRangedDamage(rangedDamage);
        
        return fightAgent;
    }
    
    
    public FightAgent demodify(FightAgent fightAgent)
    {
        int meleeDamage = fightAgent.getMeleeDamage() - meleeDamageModifier;
        float accuracy = fightAgent.getAccuracy() / accuracyModifier;
        
        fightAgent.setMeleeDamage(meleeDamage);
        fightAgent.setAccuracy(accuracy);
        
        if (!fightAgent.isRangedAttacker())
        {
            return fightAgent;
        }
        
        int rangedDamage = fightAgent.getRangedDamage() - rangedDamageModifier;
        fightAgent.setRangedDamage(rangedDamage);
        
        return fightAgent;
    }
}
