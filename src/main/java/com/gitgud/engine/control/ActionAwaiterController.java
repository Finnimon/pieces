package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.HudRender;
import javafx.concurrent.Task;


public abstract class ActionAwaiterController<ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable> extends Controller<ModelType, HudRender<ModelType>> implements Ending
{
    public ActionAwaiterController(ModelType model, HudRender<ModelType> render)
    {
        super(model, render);
    }

    
    public abstract Tile getActivePosition();
    
    
    public abstract Task<ActionChoice<ActionAwaiterController<ModelType, GridMappableType>, ModelType, GridMappableType>> getActionChoiceTask();
}
