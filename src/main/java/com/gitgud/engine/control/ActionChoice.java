package com.gitgud.engine.control;

import com.gitgud.engine.model.action.Action;
import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.HudRender;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public abstract class ActionChoice<ActionAwaiterType extends ActionAwaiterController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>> implements Named, Describable
{
    private final String name;
    
    
    private final String description;
    
    
    private final Action<ActionAwaiterType> action;
    
    
    public ActionChoice(String name, String description, Action<ActionAwaiterType> action)
    {
        this.name = name;
        this.description = description;
        this.action = action;
    }
    
    
    public ActionChoice( String name,String description)
    {
        this(name, description, null);
    }
    
    
    public void select(ActionAwaiterType actionAwaiterType)
    {
        if (action != null)
        {
            action.enAct(actionAwaiterType);
            hide(actionAwaiterType);
            return;
        }
        
        show(actionAwaiterType);
    }
    
    
    public void show(ActionAwaiterType actionAwaiter)
    {
        getHudChildren(actionAwaiter).add(getNode());
    }
    
    
    public void hide(ActionAwaiterType actionAwaiter)
    {
        getHudChildren(actionAwaiter).remove(getNode());
    }
    
    
    private ObservableList<Node> getHudChildren(ActionAwaiterType actionAwaiter)
    {
        HudRender<ModelType> render = actionAwaiter.getRender();
        return render.getHud().getChildren();
    }
    
    
    public abstract Node getNode();
    
    
    @Override
    public String description()
    {
        return this.description;
    }
    
    
    @Override
    public String name()
    {
        return this.name;
    }
}
