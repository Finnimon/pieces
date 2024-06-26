package com.gitgud.pieces.control;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.InterActionController;
import com.gitgud.engine.control.StageController;
import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.MovementRootChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.engine.model.gameobjects.interactable.Interactable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.city.City;
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
    public void start()
    {
        ActiveGameController.getInstance().get().setMission(getModel());
        super.start();
        StageController.getInstance().getStage().show();
    }
    
    
    @Override
    public void advance()
    {
        handleInteractions();
        
        if (tryEnd())
        {
            return;
        }
        super.advance();
    }
    
    
    private void handleInteractions()
    {
        
        interActionFlagger.newCheck();
        
        if (!InterActionController.hasFlag())
        {
            return;
        }
        System.out.println("MissionController.handleInteractions()");
        
        Interactable<MissionController> interactable = InterActionController.clearFlag();
        
        interactable.interact(this);
        
        if (!(interactable instanceof Collectible<MissionController>))
        {
            return;
        }
        getRender().getGridMapRender().removeGridMappable((GameObject) interactable);
    }
    
    
    @Override
    public void end()
    {
        ActiveGame activeGame = ActiveGameController.getInstance().get();
        
        activeGame.setMission(null);
        
        City city = activeGame.getCity();
        
        getModel().returnFightAgentsToPool();
        new CityController(city).start();
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
    
    
}
