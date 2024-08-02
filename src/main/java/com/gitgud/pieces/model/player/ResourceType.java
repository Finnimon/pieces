package com.gitgud.pieces.model.player;

import com.gitgud.engine.model.gameobjects.Sprite;
import org.jetbrains.annotations.NotNull;


/**
 * The Type of the {@link com.gitgud.pieces.model.player.Player} Resource.
 * <p>The basis of money.
 *
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @see com.gitgud.pieces.model.ResourceCost
 * @since 19.04.2024
 */
public enum ResourceType implements Sprite
{
    IRON,
    COPPER,
    SILVER,
    GOLD,
    PLATINUM,
    REDSTONE;
    
    
    public static final String DIR_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\player\\resourceType\\";
    
    
    @Override
    public @NotNull String getSpriteFilePath()
    {
        return DIR_PATH + name() + DOT_PNG;
    }
    
}
