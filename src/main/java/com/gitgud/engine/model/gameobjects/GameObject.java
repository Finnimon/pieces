package com.gitgud.engine.model.gameobjects;

import com.gitgud.engine.model.map.GridMap;
import com.github.ruediste.polymorphicGson.GsonPolymorph;


/**
 * Objects to be plotted on their respective {@link GridMap}.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn K.
 * @since 22.04.2022
 */
@GsonPolymorph
public class GameObject implements GridMappable
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
    public String name()
    {
        return name;
    }
    
    
    @Override
    public String description()
    {
        return description;
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
}
