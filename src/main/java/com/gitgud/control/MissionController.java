package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.mission.Mission;


//todo
public class MissionController
{
    private final Mission mission;
    
    
    private final FightAgent[] activeFightFigures;
    
    
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
