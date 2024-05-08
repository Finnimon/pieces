package com.gitgud.model;

import com.gitgud.control.FightAgentController;

import java.util.TreeSet;


public class FightTimeLine
{
    private final TreeSet<FightAgentController> timeLine;
    
    
    public FightTimeLine(TreeSet<FightAgentController> timeLine)
    {
        this.timeLine = timeLine;
    }
    
    
    public TreeSet<FightAgentController> getTimeLine()
    {
        return timeLine;
    }
    
    
}
