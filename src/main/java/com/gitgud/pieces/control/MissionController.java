package com.gitgud.pieces.control;

import com.gitgud.engine.control.ActionAwaiterController;
import com.gitgud.engine.control.ActionChoice;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;
import javafx.concurrent.Task;


//todo render as scene
public class MissionController extends ActionAwaiterController<Mission, GameObject, MissionRender>
{
    
    
    //todo render at the bottom of the screen and in selection screen
    
    
    public MissionController(Mission mission)
    {
        super(mission, new MissionRender(mission));
    }
    
    
    @Override
    public Tile getActivePosition()
    {
        return getModel().getPlayerAgentPosition();
    }
    
    
    @Override
    public Task<ActionChoice<ActionAwaiterController<Mission, GameObject, MissionRender>, Mission, GameObject, MissionRender>> getActionChoiceTask()
    {
        return null;//todo
    }
    
    
    @Override
    public void end()
    {
    
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
}
