package com.gitgud.engine.model;

import javafx.beans.property.SimpleIntegerProperty;


public interface Turning
{
    SimpleIntegerProperty turnProperty();

    default int getTurn()
    {
        return turnProperty().get();
    }

    default void incrementTurn()
    {
        turnProperty().set(turnProperty().get() + 1);
    }
}
