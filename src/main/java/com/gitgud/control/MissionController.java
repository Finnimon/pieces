package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.mission.Mission;


//todo render as scene
public class MissionController
{

    //todo render
    private final Mission mission;

    //todo render at the bottom of the screen and in selection screen

    
    
    public MissionController(Mission mission)
    {
        this.mission = mission;
    }
    
    
    public Mission getMission()
    {
        return mission;
    }

}
