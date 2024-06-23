package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import javafx.concurrent.Task;


public abstract class ActionAwaiterController
        <ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>>
        extends Controller<ModelType, RenderType> implements Ending
{
    public ActionAwaiterController(ModelType model, RenderType render)
    {
        super(model, render);
    }
    
    
    public abstract Tile getActivePosition();
    
    
    public abstract Task<ActionChoice<ActionAwaiterController<ModelType, GridMappableType, RenderType>, ModelType, GridMappableType,RenderType>> getActionChoiceTask();
}
