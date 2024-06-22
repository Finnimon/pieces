package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.RootActionChoiceRender;
import javafx.scene.Group;

import java.util.List;


public class RootActionChoice<ActionAwaiterType extends ActionAwaiterController<ModelType, GridMappableType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable> extends ActionChoice<ActionAwaiterType, ModelType, GridMappableType>
{
    private final List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType>> choices;
    
    
    private final Group node;
    
    
    public RootActionChoice(String description, String name,
                            List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType>> choices)
    {
        super(description, name);
        this.choices = choices;
        node=new RootActionChoiceRender();
    }
    
    
    public List<ActionChoice<ActionAwaiterType, ModelType, GridMappableType>> getChoices()
    {
        return choices;
        
    }
    
    
    @Override
    public Group getNode()
    {
        return node;
    }
}
