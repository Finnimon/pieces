package com.gitgud.engine.model.gameobjects;

import javafx.beans.property.SimpleIntegerProperty;


/**
 * Implemented by any object that has a level and can level up to improve or offer more functionalities.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public interface Leveler
{
    int STARTING_LEVEL = 1;
    
    
    /**
     * Getter for the level.
     *
     * @return The level.
     */
    default int getLevel()
    {
        return levelProperty().get();
    }
    
    
    /**
     * Levels up the object and returns the new Level.
     *
     * @return The new level.
     * @implNote Should be overridden by subclasses.
     */
    default int levelUp()
    {
        incrementLevel();
        return getLevel();
    }
    
    
    /**
     * Increments the level.
     */
    default void incrementLevel()
    {
        levelProperty().set(getLevel() + 1);
    }
    
    
    /**
     * Getter for the encasing Level property.
     *
     * @return The Level property.
     */
    SimpleIntegerProperty levelProperty();
}
