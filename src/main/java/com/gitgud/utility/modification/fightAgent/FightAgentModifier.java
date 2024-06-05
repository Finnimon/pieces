package com.gitgud.utility.modification.fightAgent;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.modification.Modifier;

import java.util.Collection;


public class FightAgentModifier extends Modifier<FightAgent>
{
    private final Collection<Modifier<FightAgent>> modifiers;
    
    
    public FightAgentModifier(Collection<Modifier<FightAgent>> modifiers)
    {
        this.modifiers = modifiers;
    }
    
    
    @Override
    public FightAgent modify(FightAgent fightAgent)
    {
        for (Modifier<FightAgent> modifier : modifiers)
        {
            modifier.modify(fightAgent);
        }
        
        return fightAgent;
    }
    
    
    @Override
    public FightAgent demodify(FightAgent fightAgent)
    {
        for (Modifier<FightAgent> modifier : modifiers)
        {
            modifier.demodify(fightAgent);
        }
        
        return fightAgent;
    }
    
    public static FightAgent applyModifiers(FightAgent fightAgent, Collection<Modifier<FightAgent>> modifiers)
    {
        return Modifier.applyModifiers(fightAgent, modifiers);
    }
}
