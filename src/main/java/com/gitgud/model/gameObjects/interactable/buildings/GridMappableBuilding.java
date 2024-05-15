package com.gitgud.model.gameObjects.interactable.buildings;

import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.gameObjects.interactable.Interactable;


public abstract class GridMappableBuilding extends GameObject implements Interactable
{
    public GridMappableBuilding(String name, String description, String spriteUrl)
    {
        super(name, description, spriteUrl);
    }
}
