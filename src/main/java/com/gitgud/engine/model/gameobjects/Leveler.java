package com.gitgud.engine.model.gameobjects;

import javafx.beans.property.SimpleIntegerProperty;


public interface Leveler
{
    int STARTING_LEVEL = 1;
    
    
    default int getLevel()
    {
        return levelProperty().get();
    }
    
    
    default int levelUp()
    {
        incrementLevel();
        return getLevel();
    }
    
    
    default void incrementLevel()
    {
        levelProperty().set(getLevel() + 1);
    }
    
    
    SimpleIntegerProperty levelProperty();
}
