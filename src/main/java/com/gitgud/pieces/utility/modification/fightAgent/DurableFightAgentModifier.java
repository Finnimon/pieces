package com.gitgud.pieces.utility.modification.fightAgent;

import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.utility.interfaces.Durable;

import java.util.Collection;


public class DurableFightAgentModifier extends FightAgentModifier implements Durable
{
    private final int duration;
    
    
    public DurableFightAgentModifier(Collection<Modifier<FightAgent>> modifiers, int duration)
    {
        super(modifiers);
        this.duration = duration;
    }
    
    
    @Override
    public int getDuration()
    {
        return this.duration;
    }
}
