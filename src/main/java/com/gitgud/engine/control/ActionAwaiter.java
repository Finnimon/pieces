package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.map.Tile;


public interface ActionAwaiter<ModelType extends ActionAwaiterModel<?>> extends Ending
{
    ModelType getModel();
    
    
    Tile getActivePosition();
    
    
    ActionChoice getActionChoice();
}
