package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

//todo
public class QueenBuilder extends FightAgentBuilder
{
    public QueenBuilder ()
    {
        super(FightAgent.class);
    }
    
    
    @Override
    public boolean canBuild(int type)
    {
        return false;
    }
    
    
    @Override
    public void tryBuild(int type)
    {
    
    }
}
