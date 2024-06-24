package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import javafx.scene.paint.Color;


public abstract class ActionAwaitingController
        <ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>>
        extends Controller<ModelType, RenderType> implements Ending
{
    public ActionAwaitingController(ModelType model, RenderType render)
    {
        super(model, render);
    }
    
    
    public abstract Tile getActivePosition();
    
    
    public abstract <AAType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>> ActionChoice<AAType, ModelType, GridMappableType,RenderType>getActionChoice();
    
    
    protected void hightlightActivePosition()
    {
        getRender().getGridMapRender().addHighLight(getActivePosition(), Color.BLUE);
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
}