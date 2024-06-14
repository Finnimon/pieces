package com.gitgud.engine.model.action;

import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;


public interface ActionAwaiter<T extends GridMappable>
{
    public void await();
    public HashSet<Action>  getActionsOfType(Class<Action> actionClass);
    public HashSet<Class<Action>> getAvailableActionTypes();
    public GridMap<T> getGridMap();
    
}
