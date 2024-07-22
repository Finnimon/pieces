package com.gitgud.pieces.control;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.InterActionController;
import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightTrigger;
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
        getRender().addInteractionHandlers(this);
        interActionFlagger = new InterActionFlagger(mission);
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
        ActionChoice<MissionController, Mission, GameObject, MissionRender> skipTurnChoice = getSkipTurnChoice();
        
        List<ActionChoice<MissionController, Mission, GameObject, MissionRender>> choices = List.of(rootToActionChoice,
                                                                                                    skipTurnChoice);

        return new RootActionChoice<>("root", "root", choices, this);
    }
    
    
    @Override
    public void start()
    {
        super.start();
    }
    
    
    @Override
    public void advance()
    {
        if (handleInteractions())
        {
            return;
        }
        
        if (tryEnd())
        {
            return;
        }
        
        super.advance();
    }
    
    
    private boolean handleInteractions()
    {
        
        interActionFlagger.newCheck();
        
        if (!InterActionController.hasFlag())
        {
            return false;
        }
        
        Interactable<MissionController> interactable = InterActionController.clearFlag();
        if (interactable instanceof FightTrigger)
        {
            interactable.interact(this);
            return true;
        }
        
        interactable.interact(this);
        
        return false;
    }
    
    
    @Override
    public void end()
    {
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        
        activeGame.setMission(null);
        
        getModel().returnFightAgentsToArmy();
        
        GameFlow.showNextScene();
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
    
    
}
