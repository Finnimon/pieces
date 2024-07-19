package com.gitgud.engine.model.gameobjects.interactable;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GridMappable;


public interface Interactable<ActionAwaitingType extends ActionAwaitingController> extends GridMappable
{
    void interact(ActionAwaitingType actionAwaiter);
}
