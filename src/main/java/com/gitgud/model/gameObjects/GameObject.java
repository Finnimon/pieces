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
    private String name;
    private String description;
    private String spriteUrl;
    
    public GameObject ()
    {

    }

    public GameObject (String name, String description, String spriteUrl)
    {
        this.name = name;
        this.description = description;
        this.spriteUrl = spriteUrl;
    }
    
    
    @Override
    public String getName ()
    {
        return name;
    }

    @Override
    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String getDescription ()
    {
        return description;
    }

    @Override
    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    public String getSpriteUrl ()
    {
        return spriteUrl;
    }

    @Override
    public void setSpriteUrl (String spriteUrl)
    {
        this.spriteUrl = spriteUrl;
    }

    @Override
    public int hashCode ()
    {
        return Arrays.hashCode(new Object[]{ this });
    }
}
