package com.gitgud.engine.model.gameObject.interactable;


import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;


public interface Interactable extends GridMappable
{
    void interact(GridMap gridMap);
}
