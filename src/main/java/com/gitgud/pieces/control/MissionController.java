package com.gitgud.pieces.control;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.actionChoices.MovementRootChoice;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.view.render.mission.MissionRender.MissionRender;
import javafx.beans.property.SimpleBooleanProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * Controller for the {@link Mission} Scene.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see Mission
 * @since 20.06.2024
 */
public class MissionController extends ActionAwaitingController<Mission, GameObject, MissionRender>
{
    private final SimpleBooleanProperty isFinishedProperty = new SimpleBooleanProperty(false);
    
    
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
    public @NotNull ActionChoice<MissionController, Mission, GameObject, MissionRender> getActionChoice()
    {
        RootToActionChoice<MissionController, Mission, GameObject, MissionRender> rootToActionChoice =
                new MovementRootChoice<>(
                this,
                getModel().getPlayerAgent(),
                getModel().getPlayerAgentPosition());
        ActionChoice<MissionController, Mission, GameObject, MissionRender> skipTurnChoice = getSkipTurnChoice();
        
        List<ActionChoice<MissionController, Mission, GameObject, MissionRender>> choices = List.of(rootToActionChoice,
                                                                                                    skipTurnChoice);
        
        return new RootActionChoice<>("root", "root", this, choices);
    }
    
    
    @Override
    public SimpleBooleanProperty isFinishedProperty()
    {
        return isFinishedProperty;
    }
    
    
    @Override
    public boolean isFinished()
    {
        return getModel().isFinished();
    }
    
    
    @Override
    public void end()
    {
        ActiveGameController.getInstance().get().setMission(null);
        
        getModel().end();
        
        Game.Flow.showNextScene();
    }
    
    
}
