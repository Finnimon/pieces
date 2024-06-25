package com.gitgud.pieces.control;

import com.gitgud.engine.control.*;
import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.MovementRootChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.gameobjects.interactable.FightTrigger;
import com.gitgud.pieces.model.gameobjects.interactable.buildings.MissionEnder;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;

import java.util.List;


//todo render as scene
public class MissionController extends ActionAwaitingController<Mission, GameObject, MissionRender>
{
    
    
    //todo render at the bottom of the screen and in selection screen
    private final InterActionFlagger interActionFlagger;
    
    
    public MissionController(Mission mission)
    {
        super(mission, new MissionRender(mission));
        interActionFlagger = new InterActionFlagger(mission);
    }
    
    
    @Override
    public Tile getActivePosition()
    {
        return getModel().getPlayerAgentPosition();
    }
    
    
    @SuppressWarnings("unchecked")
    @Override
    public ActionChoice<MissionController, Mission, GameObject, MissionRender> getActionChoice()
    {
        RootToActionChoice<MissionController, Mission, GameObject, MissionRender> rootToActionChoice = new MovementRootChoice<>(
                this, getModel().getPlayerAgent(), getModel().getPlayerAgentPosition());
        ActionChoice<MissionController, Mission, GameObject, MissionRender> skipTurnChoice = getSkipTurnChoice();
        
        List<ActionChoice<MissionController, Mission, GameObject, MissionRender>> choices = List.of(rootToActionChoice,
                                                                                                    skipTurnChoice);
        
        return new RootActionChoice<>("root", "root", choices, this);
    }
    
    
    
    
    
    @Override
    public void advance()
    {
        
        super.advance();
        
        
        handleInteractions();
        
        System.out.println("MissionController.advance()");
    }
    
    
    private void handleInteractions()
    {
        
        interActionFlagger.newCheck();
        
        if (!InterActionController.hasFlag())
        {
            return;
        }
        System.out.println("MissionController.handleInteractions()");
        
        Interactable interactable = InterActionController.clearFlag();
        
        
        if (interactable instanceof FightTrigger || interactable instanceof MissionEnder)
        //            interActionFlagger.wait();>
        
        {
            interactable.interact(getModel().getGridMap());
        }
        
        getRender().getGridMapRender().removeGridMappable((GameObject) interactable);
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
