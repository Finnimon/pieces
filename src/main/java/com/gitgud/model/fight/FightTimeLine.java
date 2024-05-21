package com.gitgud.model.fight;


import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class FightTimeLine
{
    
    private final TreeSet<FightAgentFL> current;
    
    
    private final TreeSet<FightAgentFL> next;
    
    
    public FightTimeLine(TreeSet<FightAgentFL> current, TreeSet<FightAgentFL> next)
    {
        this.current = current;
        this.next = next;
    }
    
    
    public FightTimeLine(Fight fight)
    {
        List<FightAgentFL>fightAgentList= fight.getGridMap().getAllGridMappables();
        this.current = new TreeSet<>(fightAgentList);
        this.next = new TreeSet<>();
    }
    
    
    public TreeSet<FightAgentFL> getCurrent()
    {
        return current;
    }
    
    
    public TreeSet<FightAgentFL> getNext()
    {
        return next;
    }
    
    
    public void advance()
    {
        TreeSet<FightAgentFL> fightTimeLine = getCurrent();
        TreeSet<FightAgentFL> nextTimeLine = getNext();
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
    
    
    public void removeDeadFightFigures(TreeSet<FightAgentFL> fightAgentTreeSet)
    {
        TreeSet<FightAgentFL> deadFigures = fightAgentTreeSet.stream().filter(Defender::isDead).collect(
                Collectors.toCollection(TreeSet::new));
        fightAgentTreeSet.removeAll(deadFigures);
    }
    
    
}
