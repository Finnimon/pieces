package com.gitgud.engine.control;

import com.gitgud.engine.model.action.Action;
import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;

import java.util.HashSet;


public interface ActionAwaiter<T extends GridMappable> extends Ending
{
    void await();
    
    
    HashSet<Action> getActionsOfType(Class<Action> actionClass);
    
    
    HashSet<Class<Action>> getAvailableActionTypes();
    
    
    GridMap<T> getGridMap();
    
}
