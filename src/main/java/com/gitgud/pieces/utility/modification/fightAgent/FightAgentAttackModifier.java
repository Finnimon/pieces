package com.gitgud.pieces.utility.modification.fightAgent;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public class FightAgentAttackModifier extends Modifier<FightAgent>
{
    private final int meleeDamageModifier;
    
    
    private final int rangedDamageModifier;
    
    
    private final double accuracyModifier;
    
    
    public FightAgentAttackModifier(int meleeDamageModifier, int rangedDamageModifier, double accuracyModifier)
    {
        this.meleeDamageModifier = meleeDamageModifier;
        this.rangedDamageModifier = rangedDamageModifier;
        this.accuracyModifier = accuracyModifier;
    }
    
    
    public FightAgent modify(FightAgent fightAgent)
    {
        int meleeDamage = fightAgent.getMeleeDamage() + meleeDamageModifier;
        double accuracy = fightAgent.getAccuracy() * accuracyModifier;
        
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
        double accuracy = fightAgent.getAccuracy() / accuracyModifier;
        
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
