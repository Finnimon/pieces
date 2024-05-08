package com.gitgud.model.gameObjects.interacteble;


import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.GridMappable;


public interface Interacteble extends GridMappable
{
    void interact(MissionController missionController);
}
