package com.gitgud.engine.model.gameObject.interactable;

import com.gitgud.engine.model.gameObject.GameObject;


public abstract class GridMappableBuilding extends GameObject implements Interactable
{
    public GridMappableBuilding(String name, String description, String spriteUrl)
    {
        super(name, description, spriteUrl);
    }
}
