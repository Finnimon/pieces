package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;
import com.gitgud.model.mission.Mission;


//todo
public class MissionController
{
    private final Mission mission;
    
    
    private final FightAgentFL[] activeFightFigures;
    
    
    private final FightAgentFL[] discardedFightFigures;
    
    
    public MissionController(Mission mission, FightAgentFL[] activeFightFigures, FightAgentFL[] discardedFightFigures)
    {
        this.mission = mission;
        this.activeFightFigures = activeFightFigures;
        this.discardedFightFigures = discardedFightFigures;
    }
    
    
    public Mission getMission()
    {
        return mission;
    }
    
    
    public FightAgentFL[] getActiveFightFigures()
    {
        return activeFightFigures;
    }
    
    
    public FightAgentFL[] getDiscardedFightFigures()
    {
        return discardedFightFigures;
    }
}
