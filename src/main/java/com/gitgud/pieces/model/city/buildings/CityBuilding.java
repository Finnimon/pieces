package com.gitgud.pieces.model.city.buildings;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Leveler;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.Sprite;


/**
 * The Buildings to be used by the {@link com.gitgud.pieces.model.city.City}.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public abstract class CityBuilding implements Describable, Named, Sprite, Leveler
{
    private static final String SPRITE_FILE_PATH_PREFIX = "com\\gitgud\\pieces\\model\\city\\buildings\\";
    
    
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteFilePath;
    
    
    private int level;
    
    
    public CityBuilding(String name, String description, int level)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = SPRITE_FILE_PATH_PREFIX + name + DOT_PNG;
        this.level = level;
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
    
    
    @Override
    public int getLevel()
    {
        return level;
    }
    
    
    @Override
    public int levelUp()
    {
        level++;
        return level;
    }
}
