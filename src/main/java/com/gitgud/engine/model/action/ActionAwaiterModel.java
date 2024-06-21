package com.gitgud.engine.model.action;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;


public interface ActionAwaiterModel<GridMappableType extends GridMappable>
{
    GridMap<GridMappableType> getGridMap();
}
