package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

//todo
public class PawnBuilder extends FightAgentBuilder
{
    public PawnBuilder()
    {
        super(FightAgent.class);
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
