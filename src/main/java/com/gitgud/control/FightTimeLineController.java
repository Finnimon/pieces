package com.gitgud.control;

import com.gitgud.model.FightTimeLine;
import com.gitgud.model.fight.Fight;

import java.util.TreeSet;
import java.util.stream.Collectors;


public class FightTimeLineController
{
    private final FightTimeLine fightTimeLine;
    
    
    private final FightTimeLine nextTimeLine = new FightTimeLine(new TreeSet<>());
    
    
    private final Fight fight;
    
    
    public FightTimeLineController(FightTimeLine fightTimeLine, FightTimeLine nextTimeLine, Fight fight)
    {
        this.fightTimeLine = fightTimeLine;
        this.fight = fight;
    }
    
    
    public FightTimeLine getFightTimeLine()
    {
        return fightTimeLine;
    }
    
    
    public Fight getFight()
    {
        return fight;
    }
    
    
    public FightTimeLine getNextTimeLine()
    {
        return nextTimeLine;
    }
    
    
    public void advance()
    {
        FightTimeLine timeLine = getFightTimeLine();
        FightTimeLine nextTimeLine = getNextTimeLine();
        removeDeadFightFigures(timeLine);
        removeDeadFightFigures(nextTimeLine);
        
        
        if (!timeLine.getTimeLine().isEmpty())
        {
            nextTimeLine.getTimeLine().add(timeLine.getTimeLine().removeFirst());
            
            return;
        }
        
        if (timeLine.getTimeLine().isEmpty())
        {
            timeLine.getTimeLine().addAll(nextTimeLine.getTimeLine());
            nextTimeLine.getTimeLine().clear();
        }
    }
    
    
    public void removeDeadFightFigures(FightTimeLine fightTimeLine)
    {
        TreeSet<FightAgentController> timeLine = fightTimeLine.getTimeLine();
        TreeSet<FightAgentController> deadFigures = timeLine.stream().filter(
                fightAgentController -> fightAgentController.getFightFigure().isDead()).collect(
                Collectors.toCollection(TreeSet::new));
        timeLine.removeAll(deadFigures);
    }
}
