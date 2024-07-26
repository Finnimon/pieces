package com.gitgud.pieces.model.fight;


import com.gitgud.engine.model.attackDefenseLogic.Defender;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;


public record FightTimeLine(TreeSet<FightAgent> current, TreeSet<FightAgent> next)
{
    
    
    public static FightTimeLine create(Collection<FightAgent> fightAgents)
    {
        TreeSet<FightAgent> current = new TreeSet<>(fightAgents);
        TreeSet<FightAgent> next = new TreeSet<>();
        
        
        return new FightTimeLine(current, next);
    }
    
    
    public synchronized void advance()
    {
        TreeSet<FightAgent> fightTimeLine = current();
        TreeSet<FightAgent> nextTimeLine = next();
        removeDeadFightAgents(fightTimeLine);
        removeDeadFightAgents(nextTimeLine);
        
        nextTimeLine.add(fightTimeLine.removeFirst());
        
        if (!fightTimeLine.isEmpty())
        {
            return;
        }
        
        fightTimeLine.addAll(nextTimeLine);
        nextTimeLine.clear();
    }
    
    
    public void removeDeadFightAgents(TreeSet<FightAgent> fightAgentTreeSet)
    {
        current.removeIf(Defender::isDead);
        next.removeIf(Defender::isDead);
    }
    
    
    public FightAgent getActiveFightAgent()
    {
        return current.first();
    }
    
    
    public ArrayList<FightAgent> getAllAgents()
    {
        ArrayList<FightAgent> fightAgents = new ArrayList<>(current);
        fightAgents.addAll(next);
        return fightAgents;
    }
    
}
