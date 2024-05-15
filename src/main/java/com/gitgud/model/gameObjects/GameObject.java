package com.gitgud.model.gameObjects;

import com.gitgud.model.fight.Fight;
import com.gitgud.model.map.GridMap;

import java.util.Arrays;


/**
 * Objects to be used in a {@link com.gitgud.model.mission.Mission} or {@link Fight} and plotted on their respective {@link GridMap}.
 *
 * @version 2.0
 * @Owner: Finn K.
 * @author  Finn L.
 * @since 22.04.2022
 */
public abstract class GameObject implements GridMappable
{
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteFilePath;
    
    
    public GameObject(String name, String description, String spriteFilePath)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
    }
    
    
    @Override
    public String getName()
    {
        return name;
    }
    
    
    @Override
    public String getDescription()
    {
        return description;
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
    
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[]{ this });
    }
}
