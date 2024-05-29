package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.mission.Mission;


//todo render as scene
public class MissionController
{

    //todo render
    private final Mission mission;

    //todo render at the bottom of the screen and in selection screen
    private final FightAgent[] activeFightFigures;

    //todo render only in selction screen
    private final FightAgent[] discardedFightFigures;
    
    
    public MissionController(Mission mission, FightAgent[] activeFightFigures, FightAgent[] discardedFightFigures)
    {
        this.mission = mission;
        this.activeFightFigures = activeFightFigures;
        this.discardedFightFigures = discardedFightFigures;
    }
    
    
    public Mission getMission()
    {
        return mission;
    }
    
    
    public FightAgent[] getActiveFightFigures()
    {
        return activeFightFigures;
    }
    
    
    public FightAgent[] getDiscardedFightFigures()
    {
        return discardedFightFigures;
    }
}
