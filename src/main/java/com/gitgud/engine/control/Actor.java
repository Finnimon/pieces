package com.gitgud.engine.control;

import com.gitgud.engine.model.action.Action;
import com.gitgud.engine.model.gameObject.GridMappable;

import java.util.HashSet;


public interface Actor<T extends GridMappable>
{
    HashSet<Action<ActionAwaiter<T>>> getActionsOfType(Class<Action<ActionAwaiter<T>>> actionClass);
    
    
    HashSet<Class<Action<ActionAwaiter<T>>>> getAvailableActionTypes();
}
