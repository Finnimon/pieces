package com.gitgud.pieces.model.fight;


import com.gitgud.engine.model.attackDefenseLogic.Defender;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * @param current todo render
 * @param next    todo render
 */
public record FightTimeLine(TreeSet<FightAgent> current, TreeSet<FightAgent> next)
{
    
    
    public static FightTimeLine create(Collection<FightAgent> fightAgents)
    {
        Comparator<FightAgent> comparator=getComparator();
        
        TreeSet<FightAgent> current = new TreeSet<>(comparator);
        
        current.addAll(fightAgents);
        
        TreeSet<FightAgent> next = new TreeSet<>(comparator);
        
        
        return new FightTimeLine(current, next);
    }
    
    
    public void advance()
    {System.out.println("\n\n\n timelinesize "+current.size());
        TreeSet<FightAgent> fightTimeLine = current();
        TreeSet<FightAgent> nextTimeLine = next();
        removeDeadFightFigures(fightTimeLine);
        removeDeadFightFigures(nextTimeLine);
        
        nextTimeLine.add(fightTimeLine.removeFirst());
        
        if (!fightTimeLine.isEmpty())
        {
            return;
        }
        
        fightTimeLine.addAll(nextTimeLine);
        nextTimeLine.clear();
    }
    
    
    public void removeDeadFightFigures(TreeSet<FightAgent> fightAgentTreeSet)
    {
        TreeSet<FightAgent> deadFigures = fightAgentTreeSet.stream().filter(Defender::isDead).collect(
                Collectors.toCollection(TreeSet::new));
        fightAgentTreeSet.removeAll(deadFigures);
    }
    
    
    public FightAgent getActiveFightAgent()
    {
        return current.first();
    }
    
    
    private static Comparator<FightAgent> getComparator()
    {
        return (o1, o2) ->o2.compareTo(o1);
    }
    
}
