package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.RootActionChoiceRender;
import javafx.scene.Group;

import java.util.List;


public class RootActionChoice<ActionAwaiterType extends ActionAwaiterController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable,RenderType extends ActionContextRender<ModelType,GridMappableType>> extends ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>
{
    private final List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>> choices;
    
    
    private final Group node;
    
    
    public RootActionChoice(String description, String name,
                            List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>> choices)
    {
        super(description, name);
        this.choices = choices;
        node=new RootActionChoiceRender();
    }
    
    
    public List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType,RenderType>> getChoices()
    {
        return choices;
    }
    
    
    @Override
    public Group getNode()
    {
        return node;//create node from choices
    }
}
