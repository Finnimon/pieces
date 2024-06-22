package com.gitgud.pieces.control;

import com.gitgud.engine.control.ActionAwaiterController;
import com.gitgud.engine.control.ActionChoice;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;


//todo render as scene
public class MissionController extends ActionAwaiterController<Mission, Interactable>
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
    public ActionChoice<MissionController, Mission, Interactable> getActionChoice()
    {
        return null;
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
