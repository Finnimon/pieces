package com.gitgud.pieces.model.gameobjects.agents;

import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;


/**
 * Class for merging FightAgents.
 * <p>Yet to implement gui feature where this can be called in missions and City.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.07.2024
 * @Version: 1.0
 */
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
        FightAgentDirector fightAgentDirector = new FightAgentDirector();
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
