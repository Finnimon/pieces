package com.gitgud.engine.model.gameobjects.interactable;


import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;


public interface Interactable extends GridMappable
{
    void interact(GridMap gridMap);
}
