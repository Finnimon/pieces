package com.gitgud.pieces.model.fight;

import com.gitgud.engine.utility.modification.DurableModifier;
import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


/**
 * Durable Modifier for {@link FightAgent}.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see DurableModifier
 * @since 16.07.2024
 */
public class DurableFightAgentModifier extends DurableModifier<FightAgent>
{
    private final Modifier<FightAgent> modifier;
    
    
    private final int duration;
    
    
    public DurableFightAgentModifier(Modifier<FightAgent> modifier, int duration)
    {
        this.modifier = modifier;
        this.duration = duration;
    }
    
    
    public Modifier<FightAgent> getModifier()
    {
        return modifier;
    }
    
    
    @Override
    public int getDuration()
    {
        return duration;
    }
    
    
    @Override
    public FightAgent modify(FightAgent fightAgent)
    {
        return modifier.modify(fightAgent);
    }
    
    
    @Override
    public FightAgent demodify(FightAgent fightAgent)
    {
        return modifier.demodify(fightAgent);
    }
    
}
