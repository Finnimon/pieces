package com.gitgud.engine.control;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.utility.Strings;
import com.gitgud.engine.view.ActionContextRender;


public abstract class ActionAwaitingController
        <ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>>
        extends Controller<ModelType, RenderType> implements Ending
{
    public ActionAwaitingController(ModelType model, RenderType render)
    {
        super(model, render);
        start();
    }
    
    
    public abstract Tile getActivePosition();
    
    
    public abstract <AAType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>> ActionChoice<AAType, ModelType, GridMappableType,RenderType> getActionChoice();
    
    
    protected void hightlightActivePosition()
    {
        System.out.println("TODO: Hightlighting in com\\gitgud\\engine\\control\\ActionAwaitingController#hightlightActivePosition()");
        
        //todo fix currently not working as it blocks the InfoPane event for the active Agent underneath the highlight
        getRender().getGridMapRender().addHighLight(getActivePosition()).toBack();
    }
    
    
    @Override
    public void advance()
    {
        if (tryEnd())
        {
            return;
        }
        getRender().getGridMapRender().clearHighLights();
        getActionChoice().show(this);
        hightlightActivePosition();
    }
    
    public void start()
    {
        getRender().getGridMapRender().clearHighLights();
        getActionChoice().show(this);
        
         hightlightActivePosition();
    }
    
    protected <AAType extends ActionAwaitingController<ModelType, GridMappableType,RenderType>>ActionChoice<AAType, ModelType, GridMappableType, RenderType> getSkipTurnChoice()
    {
        return (ActionChoice<AAType, ModelType, GridMappableType, RenderType>) ActionChoice.empty("Skip" + Strings.LINE_BREAK + "Turn", "Skip this turn.", this);
    }
}
