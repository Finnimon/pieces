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
    int getHealth();
    
    
    void setHealth(int health);
    
    
    int getMaxHealth();
    
    
    SimpleIntegerProperty healthProperty();
    
    
    SimpleIntegerProperty maxHealthProperty();
}
