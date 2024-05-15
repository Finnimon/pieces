package com.gitgud.control;


import com.gitgud.model.fight.FightTimeLine;
import com.gitgud.model.gameObjects.interactable.FightTrigger;
import com.gitgud.model.fight.Fight;


public class FightController implements Runnable
{
    private final Fight fight;
    
    
    private final MissionController missionController;
    
    
    private final FightTimeLine fightTimeLine;
    
    
    private int turn = 0;
    
    
    public FightController(MissionController missionController, FightTrigger fightTrigger)
    {
        this.fight = fightTrigger.getFight();
        this.missionController = missionController;
        
        this.fightTimeLine = new FightTimeLine(fight);
    }
    
    
    public Fight getFight()
    {
        return fight;
    }
    
    
    @Override
    public void run()
    {
    
    }
    
    
    public MissionController getMissionController()
    {
        return missionController;
    }
    
    
    public int getTurn()
    {
        return turn;
    }
    
    
    public int increaseTurn()
    {
        return ++turn;
    }
    
    
    public FightTimeLine getFightTimeLineController()
    {
        return fightTimeLine;
    }
}
