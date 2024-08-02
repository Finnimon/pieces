package com.gitgud.engine.model.gameobjects.agent;

import com.gitgud.engine.model.attackDefenseLogic.Attacker;
import com.gitgud.engine.model.attackDefenseLogic.Defender;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Collection;


/**
 * An Agent that also offers attack and defense capabilities.
 *
 * @Owner: Finn L.
 * @Author: Finn L.
 * @Since: 12.06.2024
 * @Version: 1.0
 */

public abstract class Fighter extends Agent implements Attacker, Defender
{
    public Fighter(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
    }
    
    
    /**
     * Find all Tiles, that can be attacked as of now by this {@link Fighter}.
     *
     * @param gridMap  The {@link GridMap} to search in.
     * @param position The {@link Tile} to search around.
     * @return All attackable {@link Tile}s.
     */
    public abstract Collection<Tile> findAttackableTiles(GridMap gridMap, Tile position);
    
    
    /**
     * Getter for {@link #maxManaProperty()}
     *
     * @return {@link #maxManaProperty()} value
     */
    public int getMaxMana()
    {
        return maxManaProperty().get();
    }
    
    
    /**
     * The maximum mana property of this {@link Fighter}.
     *
     * @return The maximum mana property.
     */
    public abstract SimpleIntegerProperty maxManaProperty();
    
    
    /**
     * Setter for {@link #maxManaProperty()} value
     */
    public void setMaxMana(int maxMana)
    {
        maxManaProperty().setValue(maxMana);
    }
    
    
    /**
     * Getter for {@link #manaProperty()} value
     *
     * @return {@link #manaProperty()} value
     */
    public int getMana()
    {
        return manaProperty().get();
    }
    
    
    /**
     * Setter for the manaProperty.
     *
     * @param mana The new value for the mana.
     */
    public void setMana(int mana)
    {
        this.manaProperty().setValue(mana);
    }
    
    
    /**
     * Getter for the mana Property.
     *
     * @return The mana Property.
     */
    public abstract SimpleIntegerProperty manaProperty();
    
}
