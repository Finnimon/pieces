package com.gitgud.engine.model;

import javafx.beans.property.SimpleIntegerProperty;


public interface Turning
{
    default int getTurn()
    {
        return turnProperty().get();
    }
    
    
    SimpleIntegerProperty turnProperty();
    
    
    default void incrementTurn()
    {
        turnProperty().set(turnProperty().get() + 1);
    }
}
