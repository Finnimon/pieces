package com.gitgud.pieces.control;

import com.gitgud.engine.control.Controller;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.MissionRender;


//todo render as scene
public class MissionController extends Controller<Mission>
{
    
    //todo render
    private final Mission mission;
    
    //todo render at the bottom of the screen and in selection screen
    
    
    public MissionController(Mission mission)
    {
        super(mission,new MissionRender());
        this.mission = mission;
    }
    
    
    public Mission getMission()
    {
        return mission;
    }
    
}
