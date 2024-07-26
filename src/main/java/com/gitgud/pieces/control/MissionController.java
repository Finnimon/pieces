package com.gitgud.pieces.control;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;

import java.util.List;


//todo render as scene
public class MissionController extends ActionAwaitingController<Mission, GameObject, MissionRender>
{
    
    public MissionController(Mission mission)
    {
        super(mission, new MissionRender(mission));
        getRender().addInteractionHandlers(this);
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
                this,
                getModel().getPlayerAgent(),
                getModel().getPlayerAgentPosition());
        ActionChoice<MissionController, Mission, GameObject, MissionRender> skipTurnChoice = getSkipTurnChoice();
        
        List<ActionChoice<MissionController, Mission, GameObject, MissionRender>> choices = List.of(rootToActionChoice,
                                                                                                    skipTurnChoice);
        
        return new RootActionChoice<>("root", "root", choices, this);
    }
    
    
    @Override
    public void advance()
    {
        if (tryEnd())
        {
            return;
        }
        
        super.advance();
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
