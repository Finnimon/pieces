package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.ActionContextRender;

import java.util.List;


public class RootToActionChoice<ActionAwaitingType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>> extends
                                                                                                                                                                                                                                                                                                  ActionChoice<ActionAwaitingType, ModelType, GridMappableType, RenderType> implements
                                                                                                                                                                                                                                                                                                                                                                            RootChoice<ToActionChoice<ActionAwaitingType, ModelType, GridMappableType, RenderType>>
{
    private final List<ToActionChoice<ActionAwaitingType, ModelType, GridMappableType, RenderType>> toActionChoices;
    
    
    public RootToActionChoice(String name, String description, ActionAwaitingType awaiter,
                              List<ToActionChoice<ActionAwaitingType, ModelType, GridMappableType, RenderType>> toActionChoices)
    {
        super(name, description, awaiter);
        this.toActionChoices = toActionChoices;
    }
    
    
    public List<ToActionChoice<ActionAwaitingType, ModelType, GridMappableType, RenderType>> getChoices()
    {
        return toActionChoices;
    }
    
    
    @Override
    public void show(ActionAwaitingType actionAwaiter)
    {
        for (ToActionChoice<ActionAwaitingType, ModelType, GridMappableType, RenderType> actionChoice : toActionChoices)
        {
            actionChoice.show(actionAwaiter);
        }
    }
}
