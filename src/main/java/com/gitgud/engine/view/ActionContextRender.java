package com.gitgud.engine.view;

import com.gitgud.engine.model.ActionAwaiterModel;


/**
 *
 * @param <ModelType>
 * @param <GridMappableType>
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public interface ActionContextRender<ModelType extends ActionAwaiterModel<GridMappableType>,
        GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable>
        extends HudRender<ModelType>
{
    GridMapRender<GridMappableType> getGridMapRender();
    
    
    @Override
    ActionContextHud<ModelType> getHud();
    
    
    void show();
}
