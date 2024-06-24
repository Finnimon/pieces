package com.gitgud.pieces.control;

import com.gitgud.engine.control.*;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.gameobjects.interactable.FightTrigger;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.MissionEnder;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;

import java.util.List;


//todo render as scene
public class MissionController extends ActionAwaitingController<Mission, GameObject, MissionRender>
{
    
    
    //todo render at the bottom of the screen and in selection screen
    private final Thread interActionFlagger;
    
    public MissionController(Mission mission)
    {
        super(mission, new MissionRender(mission));
        
       (interActionFlagger= new Thread(new InterActionFlagger(mission))).start();
    }
    
    
    @Override
    public Tile getActivePosition()
    {
        return getModel().getPlayerAgentPosition();
    }
    
    
    @Override
    public ActionChoice<MissionController, Mission, GameObject, MissionRender> getActionChoice()
    {
        RootToActionChoice<MissionController, Mission, GameObject, MissionRender> rootToActionChoice = new MovementRootChoice<>(
                this, getModel().getPlayerAgent(), getModel().getPlayerAgentPosition());
        ActionChoice<MissionController,Mission,GameObject,MissionRender> empty=ActionChoice.empty("Turnskip","Skip this turn.",this);
        
        List<ActionChoice<MissionController,Mission,GameObject,MissionRender>> choices = List.of(rootToActionChoice,empty);
        
        return new RootActionChoice<>("root","root",choices,this);
    }
    
    
    @Override
    public void advance()
    {
        super.advance();
        
    }
    
    private void handleInteractions()
    {
        if (!InterActionController.hasFlag())
            return;
        
        Interactable interactable= InterActionController.clearFlag();
        
        if (interactable instanceof FightTrigger||interactable instanceof MissionEnder)
//            interActionFlagger.wait();
        
            interactable.interact(getModel().getGridMap());
    }
    
    
    @Override
    public void end()
    {
        ActiveGameController.getInstance().get().setMission(null);
        
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
    
    
}
