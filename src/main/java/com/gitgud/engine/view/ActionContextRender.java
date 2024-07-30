package com.gitgud.engine.view;

import com.gitgud.engine.model.ActionAwaiterModel;


public interface ActionContextRender<ModelType extends ActionAwaiterModel<GridMappableType>,
        GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable>
        extends HudRender<ModelType>
{
    GridMapRender<GridMappableType> getGridMapRender();
    
    
    @Override
    ActionContextHud<ModelType> getHud();
    
    
    void show();
}
