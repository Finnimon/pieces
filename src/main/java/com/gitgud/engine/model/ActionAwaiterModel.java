package com.gitgud.engine.model;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;


/**
 * The Model for an {@link com.gitgud.engine.control.ActionAwaitingController}.
 *
 * @param <GridMappableType> The Type of the {@link GridMappable} on this model's {@link GridMap}.
 * @Owner: Finn L.
 * @Author: Finn L.
 * @Since: 12.06.2024
 * @Version: 1.1
 */
public interface ActionAwaiterModel<GridMappableType extends GridMappable> extends Turning
{
    /**
     * Getter for the model's {@link GridMap}.
     *
     * @return The model's {@link GridMap}.
     */
    GridMap<GridMappableType> getGridMap();
}
