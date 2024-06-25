package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.RootActionChoiceRender;

import java.util.List;


public class RootActionChoice<ActionAwaiterType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable,RenderType extends ActionContextRender<ModelType,GridMappableType>> extends ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>implements RootChoice
{
    private final List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>> choices;
    
    
    
    
    public RootActionChoice(String name, String description,
                            List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>> choices,ActionAwaiterType actionAwaiterType)
    {
        super(name, description, actionAwaiterType);
        this.choices = choices;
    }
    
    
    public List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>> getChoices()
    {
        return choices;
    }
    
    
    @Override
    public void show(ActionAwaiterType actionAwaiter)
    {
        RootActionChoiceRender render=new RootActionChoiceRender(this);
        actionAwaiter.getRender().getHud().addChoice(render);
    }
    
    public boolean isEmpty()
    {
        return choices.isEmpty();
    }
    
    
}
