package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;

//todo
public class BishopBuilder extends FightAgentBuilder
{
    public BishopBuilder()
    {
        super(SpellCasterFightAgent.class);
    }
    
    
    @Override
    public void tryBuild(int type)
    {
    
    }
    
    
    @Override
    public boolean canBuild(int type)
    {
        return false;
    }
}
