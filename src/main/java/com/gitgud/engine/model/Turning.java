package com.gitgud.engine.model;

import javafx.beans.property.SimpleIntegerProperty;


/**
 * Used to keep track of the turns in game situations that are turn-based.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 20.04.2024
 * @Version: 1.0
 */
public interface Turning
{
    /**
     * Getter for the current turn.
     * @return The current turn.
     */
    default int getTurn()
    {
        return turnProperty().get();
    }
    
    
    /**
     * Getter for the turn property.
     * @return The turn property.
     */
    SimpleIntegerProperty turnProperty();
    
    
    /**
     * Increments the turn by 1.
     */
    default void incrementTurn()
    {
        turnProperty().set(turnProperty().get() + 1);
    }
}
