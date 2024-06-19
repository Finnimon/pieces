package com.gitgud.engine.model.action;

import com.gitgud.engine.control.Ending;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;

import java.util.HashSet;


public interface ActionAwaiter<ActorType extends Actor> extends Ending
{
    
    GridMap getGridMap();
    Tile getActivePosition();
    HashSet<Class> getAvailableActionTypes();
    
    HashSet<Action> getActionsOfType(Class actionClass);
}
