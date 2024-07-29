package com.gitgud.engine.model.attackDefenseLogic;

import javafx.beans.property.SimpleIntegerProperty;


/**
 * Objects that have a health stat.
 * implemented by {@link Defender}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface Health
{
    default int getHealth()
    {
        return healthProperty().getValue();
    }
    
    
    default void setHealth(int health)
    {
        healthProperty().setValue(health);
    }
    
    
    default int getMaxHealth()
    {
        return maxHealthProperty().getValue();
    }
    
    
    default void setMaxHealth(int maxHealth)
    {
        maxHealthProperty().setValue(maxHealth);
    }
    
    
    SimpleIntegerProperty healthProperty();
    
    
    SimpleIntegerProperty maxHealthProperty();
}
