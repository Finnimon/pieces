package com.gitgud.engine.control;

import com.gitgud.engine.model.action.Action;
import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;

import java.util.HashSet;


public interface ActionAwaiter<T extends GridMappable> extends Ending
{
    void await();
    
    
    public Actor<T> getActiveActor();
    
    
    GridMap<T> getGridMap();
}
