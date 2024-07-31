package com.gitgud.engine.view;

import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;


/**
 * <p>This is the render deployed by the Framework class {@link com.gitgud.engine.control.ActionAwaitingController}.
 * <p>It always has a {@link ActionContextHud} and a {@link GridMapRender}.
 *
 * @param <ModelType> The type if {@link ActionAwaiterModel} used by this Render.
 * @param <GridMappableType> The Type of the {@link GridMappable} on this model's {@link GridMap}.
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public interface ActionContextRender<ModelType extends ActionAwaiterModel<GridMappableType>,
        GridMappableType extends GridMappable>
        extends HudRender<ModelType>
{
    /**
     * Getter for the GridMapRender.
     * @return The GridMapRender.
     */
    GridMapRender<GridMappableType> getGridMapRender();
    
    
    @Override
    ActionContextHud<ModelType> getHud();
    
    
    /**
     * Allows the {@link com.gitgud.engine.control.ActionAwaitingController} to access the SceneGraph and show this Render.
     */
    void show();
}
