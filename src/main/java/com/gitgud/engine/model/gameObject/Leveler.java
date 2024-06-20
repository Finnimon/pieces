package com.gitgud.engine.model.gameObject;

public interface Leveler
{
    int STARTING_LEVEL = 1;
    
    
    int getLevel();
    
    
    int levelUp();
}
