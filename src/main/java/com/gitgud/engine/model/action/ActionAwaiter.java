package com.gitgud.engine.model.action;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;


public interface ActionAwaiter<From,To,Target> extends ExecutorService
{
    public void await();
    public HashSet<Action>  getActionsOfType(Class<Action> actionClass);
    public HashSet<Class<Action>> getAvailableActionTypes();
    
}
