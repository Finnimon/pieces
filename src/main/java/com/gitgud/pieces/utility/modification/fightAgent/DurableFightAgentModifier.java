package com.gitgud.pieces.utility.modification.fightAgent;

import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.utility.interfaces.Durable;
import com.gitgud.engine.utility.modification.Modifier;

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
