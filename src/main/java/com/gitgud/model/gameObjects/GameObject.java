package com.gitgud.model.gameObjects;

import com.gitgud.model.mission.GridMapContext;
import com.gitgud.model.mission.map.GridMapping;
import com.gitgud.model.mission.map.Tile;
import com.gitgud.model.mission.fight.Fight;
import com.gitgud.model.mission.map.GridMap;
import com.gitgud.utility.interfaces.GridMappable;

import java.util.Arrays;


/**
 * Objects to be used in a {@link GridMapContext} or {@link Fight} and plotted on their respective {@link GridMap}.
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
    
    
    private final String spriteUrl;
    
    
    public GameObject(String name, String description, String spriteUrl)
    {
        this.name = name;
        this.description = description;
        this.spriteUrl = spriteUrl;
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
    public String getSpriteUrl()
    {
        return spriteUrl;
    }
    
    
    @Override
    public GridMapping getMappingTo(Tile tile)
    {
        return new GridMapping(this, tile);
    }
    
    
    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[]{ this });
    }
}
