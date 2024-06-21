package com.gitgud.pieces.utility.modification.fightAgent;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public class FightAgentDefenceModifier extends Modifier<FightAgent>
{
    private final int physicalDefenceModifier;
    
    
    private final int magicDefenceModifier;
    
    
    private final float evadeChanceModifier;
    
    
    public FightAgentDefenceModifier(int physicalDefenceModifier, int magicDefenceModifier, float evadeChanceModifier)
    {
        this.physicalDefenceModifier = physicalDefenceModifier;
        this.magicDefenceModifier = magicDefenceModifier;
        this.evadeChanceModifier = evadeChanceModifier;
    }
    
    
    @Override
    public FightAgent modify(FightAgent fightAgent)
    {
        int physicalDefence = fightAgent.getPhysicalDefence() + physicalDefenceModifier;
        int magicDefence = fightAgent.getMagicDefence() + magicDefenceModifier;
        float evadeChance = fightAgent.getEvadeChance() * evadeChanceModifier;
        fightAgent.setPhysicalDefence(physicalDefence);
        fightAgent.setMagicDefence(magicDefence);
        fightAgent.setEvadeChance(evadeChance);
        return fightAgent;
    }
    
    
    @Override
    public FightAgent demodify(FightAgent fightAgent)
    {
        int physicalDefence = fightAgent.getPhysicalDefence() - physicalDefenceModifier;
        int magicDefence = fightAgent.getMagicDefence() - magicDefenceModifier;
        float evadeChance = fightAgent.getEvadeChance() / evadeChanceModifier;
        fightAgent.setPhysicalDefence(physicalDefence);
        fightAgent.setMagicDefence(magicDefence);
        fightAgent.setEvadeChance(evadeChance);
        return fightAgent;
    }
}
