package com.gitgud.control;


import com.gitgud.model.gameObjects.interacteble.FightTrigger;
import com.gitgud.model.fight.Fight;


public class FightController implements Runnable
{
    private final Fight fight;
    
    
    private final MissionController missionController;
    
    
    private int turn = 0;
    
    
    public FightController(MissionController missionController, FightTrigger fightTrigger)
    {
        this.fight = fightTrigger.getFight();
        this.missionController = missionController;
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
}
