package com.gitgud.engine.model;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;


public interface ActionAwaiterModel<GridMappableType extends GridMappable> extends Turning
{
    GridMap<GridMappableType> getGridMap();
}
