package com.gitgud.engine.model.gameobjects.interactable;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GameObject;


public abstract class GridMappableBuilding<AAType extends ActionAwaitingController> extends GameObject implements Interactable<AAType>
{
    public GridMappableBuilding(String name, String description, String spriteUrl)
    {
        super(name, description, spriteUrl);
    }
}
