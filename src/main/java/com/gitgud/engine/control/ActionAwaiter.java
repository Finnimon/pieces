package com.gitgud.engine.control;

import com.gitgud.engine.model.action.Action;
import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;

import java.util.HashSet;


public interface ActionAwaiter<T extends GridMappable> extends Ending
{
    void await();
    
    
    HashSet<Action<ActionAwaiter<T>>> getActionsOfType(Class<Action<ActionAwaiter<T>>> actionClass);
    
    
    HashSet<Class<Action<ActionAwaiter<T>>>> getAvailableActionTypes();
    
    
    GridMap<T> getGridMap();
    
}
