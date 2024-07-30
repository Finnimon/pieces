package com.gitgud.engine.model.attackDefenseLogic;

import javafx.beans.property.SimpleIntegerProperty;


/**
 * Objects that have a health stat.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 * @see Defender
 */
public interface Health
{
    /**
     * Getter for the health stat.
     * @return The health stat.
     */
    default int getHealth()
    {
        return healthProperty().getValue();
    }
    
    
    /**
     * Setter for the health stat.
     * @param health The new health stat value.
     */
    default void setHealth(int health)
    {
        healthProperty().setValue(health);
    }
    
    
    /**
     * Getter for the health property.
     * @return The health property.
     */
    SimpleIntegerProperty healthProperty();
    
    
    /**
     * Getter for the maxHealth stat.
     * @return The maxHealth stat.
     */
    default int getMaxHealth()
    {
        return maxHealthProperty().getValue();
    }
    
    /**
     * Setter for the maxHealth stat.
     * @param maxHealth The new maxHealth stat value.
     */
    default void setMaxHealth(int maxHealth)
    {
        maxHealthProperty().setValue(maxHealth);
    }
    
    /**
     * Getter for the maxHealth property.
     * @return The maxHealth property.
     */
    SimpleIntegerProperty maxHealthProperty();
}
