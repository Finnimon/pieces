package com.gitgud.model.gameObjects.interacteble.buildings;

import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.utility.interfaces.Interacteble;


public abstract class GridMappableBuilding extends GameObject implements Interacteble
{
    public GridMappableBuilding(String name, String description, String spriteUrl)
    {
        super(name, description, spriteUrl);
    }
}
