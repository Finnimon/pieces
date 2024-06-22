package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.view.HudRender;
import javafx.collections.ObservableList;
import javafx.scene.Node;


public interface ActionChoice<ActionAwaiterType extends ActionAwaiterController<ModelType, GridMappableType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable> extends Named, Describable
{
    void select(ActionAwaiterType actionAwaiter);
    
    
    default void show(ActionAwaiterType actionAwaiter)
    {
        getHudChildren(actionAwaiter).add(getNode());
    }
    
    
    default void hide(ActionAwaiterType actionAwaiter)
    {
        getHudChildren(actionAwaiter).remove(getNode());
    }
    
    
    private ObservableList<Node> getHudChildren(ActionAwaiterType actionAwaiter)
    {
        HudRender<ModelType> render = actionAwaiter.getRender();
        return render.getHud().getChildren();
    }
    
    
    Node getNode();
}
