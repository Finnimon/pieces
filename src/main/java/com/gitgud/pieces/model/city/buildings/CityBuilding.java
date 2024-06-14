package com.gitgud.pieces.model.city.buildings;

import com.gitgud.engine.model.gameObject.Describable;
import com.gitgud.engine.model.gameObject.Named;
import com.gitgud.engine.model.gameObject.Sprite;


/**
 * The Buildings to be used by the {@link com.gitgud.pieces.model.city.City}.
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public abstract class CityBuilding implements Describable, Named, Sprite
{
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteUrl;
    
    
    private int level;
    
    
    public CityBuilding(String name, String description, String spriteFilePath, int level)
    {
        this.name = name;
        this.description = description;
        this.spriteUrl = spriteFilePath;
        this.level = level;
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
        return spriteUrl;
    }
    
    
    public int getLevel()
    {
        return level;
    }
    
    
    public int levelUp()
    {
        level++;
        return level;
    }
}
