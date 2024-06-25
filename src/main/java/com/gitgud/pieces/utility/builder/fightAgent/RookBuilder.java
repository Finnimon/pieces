package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

//todo
public class RookBuilder extends FightAgentBuilder
{
    public RookBuilder()
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
