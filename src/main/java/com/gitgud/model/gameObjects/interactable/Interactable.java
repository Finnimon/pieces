package com.gitgud.model.gameObjects.interactable;


import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.GridMappable;


public interface Interactable extends GridMappable
{
    void interact(MissionController missionController);
}
