package com.gitgud.engine.model.gameobjects.interactable;

import com.gitgud.engine.model.gameobjects.GameObject;


public abstract class GridMappableBuilding extends GameObject implements Interactable
{
    public GridMappableBuilding(String name, String description, String spriteUrl)
    {
        super(name, description, spriteUrl);
    }
}
