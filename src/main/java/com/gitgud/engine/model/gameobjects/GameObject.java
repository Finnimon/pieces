package com.gitgud.engine.model.gameobjects;

import com.gitgud.engine.model.map.GridMap;
import com.github.ruediste.polymorphicGson.GsonPolymorph;
import org.jetbrains.annotations.NotNull;


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
    /**
     * The name of the GameObject.
     */
    private final String name;
    
    
    /**
     * The description of the GameObject.
     */
    private final String description;
    
    
    /**
     * The path to the sprite of the GameObject.
     */
    private final String spriteFilePath;
    
    
    /**
     * Default Constructor that assigns all properties.
     *
     * @param name           The name of the GameObject.
     * @param description    The description of the GameObject.
     * @param spriteFilePath The path to the sprite of the GameObject.
     */
    public GameObject(@NotNull String name, @NotNull String description, @NotNull String spriteFilePath)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
    }
    
    
    @Override
    public @NotNull String name()
    {
        return name;
    }
    
    
    @Override
    public @NotNull String description()
    {
        return description;
    }
    
    
    @Override
    public @NotNull String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
}
