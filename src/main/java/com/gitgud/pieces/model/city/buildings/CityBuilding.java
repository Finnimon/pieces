package com.gitgud.pieces.model.city.buildings;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Leveler;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.Sprite;
import javafx.beans.property.SimpleIntegerProperty;
import org.jetbrains.annotations.NotNull;


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
    /**
     * The dir in which all the sprites are stored.
     */
    private static final String SPRITE_FILE_PATH_PREFIX = "com\\gitgud\\pieces\\model\\city\\buildings\\";
    
    
    /**
     * The name of the building.
     */
    private final String name;
    
    
    /**
     * The description of the building.
     */
    private final String description;
    
    
    /**
     * The path to the sprite of the building.
     */
    private final String spriteFilePath;
    
    
    /**
     * The level of the building.
     */
    private final SimpleIntegerProperty level;
    
    
    /**
     * Assigns all the values for the building and determines the sprite path
     *
     * @param name
     * @param description
     * @param level
     */
    public CityBuilding(String name, String description, int level)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = SPRITE_FILE_PATH_PREFIX + name + DOT_PNG;
        this.level = new SimpleIntegerProperty(level);
    }
    
    
    @Override
    public final String name()
    {
        return name;
    }
    
    
    @Override
    public final String description()
    {
        return description;
    }
    
    
    @Override
    public final @NotNull String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
    
    @Override
    public final SimpleIntegerProperty levelProperty()
    {
        return level;
    }
}
