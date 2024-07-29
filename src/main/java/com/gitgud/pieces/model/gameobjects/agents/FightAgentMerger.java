package com.gitgud.pieces.model.gameobjects.agents;

import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;


public class FightAgentMerger
{
    private final FightAgent first;
    
    
    private final FightAgent second;
    
    
    public FightAgentMerger(FightAgent first, FightAgent second)
    {
        this.first = first;
        this.second = second;
    }
    
    
    public FightAgent getFirst()
    {
        return first;
    }
    
    
    public FightAgent getSecond()
    {
        return second;
    }
    
    
    public boolean canMerge()
    {
        FightAgentDirector fightAgentDirector=new FightAgentDirector();
        return first != second && fightAgentDirector.calculateType(first) == fightAgentDirector.calculateType(second);
    }
    
    
    public void merge()
    {
        removeSecondFromGame();
        first.levelUp();
    }
    
    
    private void removeSecondFromGame()
    {
        //todo
    }
}
