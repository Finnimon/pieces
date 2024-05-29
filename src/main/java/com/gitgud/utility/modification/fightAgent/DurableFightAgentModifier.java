package com.gitgud.utility.modification.fightAgent;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.interfaces.Durable;
import com.gitgud.utility.modification.Modifier;

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
