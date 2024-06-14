package com.gitgud.pieces.model.fight;


import com.gitgud.engine.model.gameObject.agent.attackDefenseLogic.Defender;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class FightTimeLine
{
    //todo render
    private final TreeSet<FightAgent> current;

    //todo render
    private final TreeSet<FightAgent> next;
    
    
    public FightTimeLine(TreeSet<FightAgent> current, TreeSet<FightAgent> next)
    {
        this.current = current;
        this.next = next;
    }
    
    
    public FightTimeLine(Fight fight)
    {
        List<FightAgent>fightAgentList= fight.getGridMap().getAllGridMappables();
        this.current = new TreeSet<>(fightAgentList);
        this.next = new TreeSet<>();
    }
    
    
    public TreeSet<FightAgent> getCurrent()
    {
        return current;
    }
    
    
    public TreeSet<FightAgent> getNext()
    {
        return next;
    }
    
    
    public void advance()
    {
        TreeSet<FightAgent> fightTimeLine = getCurrent();
        TreeSet<FightAgent> nextTimeLine = getNext();
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
    
    
}
