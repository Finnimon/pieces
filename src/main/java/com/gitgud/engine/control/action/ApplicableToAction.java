package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.action.types.Applicable;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;


public interface ApplicableToAction<ActionAwaiterControllerType extends ActionAwaitingController<ModelType, TargetType,RenderType>, ApplicableType extends Applicable<TargetType>, TargetType extends GridMappable, ModelType extends ActionAwaiterModel<TargetType>,RenderType extends ActionContextRender<ModelType, TargetType>> extends ToAction<ActionAwaiterControllerType, Tile>
{
    @Override
    default void enAct(ActionAwaiterControllerType awaiter)
    {
        GridMap<TargetType> gridMap = awaiter.getModel().getGridMap();
        getApplicable().apply(gridMap.get(getTo()));
    }
    
    
    ApplicableType getApplicable();
}
