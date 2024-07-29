package com.gitgud.engine.control;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.utility.Strings;
import com.gitgud.engine.view.ActionContextRender;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public abstract class ActionAwaitingController<ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>> extends
                                                                                                                                                                                                                   Controller<ModelType, RenderType> implements
                                                                                                                                                                                                                                                     Ending,
                                                                                                                                                                                                                                                     Startable
{
    public ActionAwaitingController(ModelType model, RenderType render)
    {
        super(model, render);
    }
    
    
    public abstract Tile getActivePosition();
    
    
    public abstract <AAType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>> ActionChoice<AAType, ModelType, GridMappableType, RenderType> getActionChoice();
    
    
    protected void hightlightActivePosition()
    {
        //todo fix currently not working as it blocks the InfoPane event for the active Agent underneath the highlight
        Tile tile = getActivePosition();
        if (tile == null)
        {
            return;
        }
        getRender().getGridMapRender().addHighLight(tile);
    }
    
    
    @Override
    public void advance()
    {
        if (tryEnd())
        {
            return;
        }
        getModel().incrementTurn();
        getRender().getGridMapRender().clearHighLights();
        showRootAction();
        hightlightActivePosition();
        getRender().updateRender();
    }
    
    
    public void showRootAction()
    {
        executeActionChoiceTask(actionChoice -> actionChoice.show(this));
    }
    
    
    public void start()
    {
        getRender().show();
        showRootAction();
    }
    
    
    protected <AAType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>> ActionChoice<AAType, ModelType, GridMappableType, RenderType> getSkipTurnChoice()
    {
        return (ActionChoice<AAType, ModelType, GridMappableType, RenderType>) ActionChoice.empty("Skip" +
                                                                                                  Strings.LINE_BREAK +
                                                                                                  "Turn",
                                                                                                  "Skip this turn.",
                                                                                                  this);
    }
    
    
    private Task<ActionChoice> actionChoiceTask()
    {
        return new Task<>()
        {
            @Override
            protected ActionChoice call()
            {
                return getActionChoice();
            }
        };
    }
    
    
    public void executeActionChoiceTask(Consumer<ActionChoice> onSucceeded)
    {
        Task<ActionChoice> task = actionChoiceTask();
        task.setOnSucceeded(x -> onSucceeded.accept(task.getValue()));
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(task);
        exec.shutdown();
    }
}
