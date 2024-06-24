package com.gitgud.pieces.utility.modification.fightAgent;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

import java.util.ArrayList;
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
    
    
    public ArrayList<Modifier<FightAgent>> getModifiers()
    {
        return (ArrayList<Modifier<FightAgent>>) this.modifiers;
    }
    
    
    public static FightAgent applyModifiers (FightAgent fightAgent, Collection<Modifier<FightAgent>> modifiers)
    {
        return Modifier.applyModifiers(fightAgent, modifiers);
    }
}
