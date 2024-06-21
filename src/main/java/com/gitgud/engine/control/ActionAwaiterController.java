package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.HudRender;


public abstract class ActionAwaiterController<ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable> extends Controller<ModelType, HudRender<ModelType>> implements ActionAwaiter<ModelType>
{
    public ActionAwaiterController(ModelType model, HudRender<ModelType> render)
    {
        super(model, render);
    }
}
