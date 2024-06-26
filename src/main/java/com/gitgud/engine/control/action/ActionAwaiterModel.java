package com.gitgud.engine.control.action;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;


public interface ActionAwaiterModel<GridMappableType extends GridMappable>
{
    GridMap<GridMappableType> getGridMap();
}
