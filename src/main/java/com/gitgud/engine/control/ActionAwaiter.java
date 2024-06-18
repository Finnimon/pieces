package com.gitgud.engine.control;

import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;


public interface ActionAwaiter<T extends GridMappable> extends Ending
{
    void await();
    
    
    Actor<T> getActiveActor();
    
    
    GridMap<T> getGridMap();
}
