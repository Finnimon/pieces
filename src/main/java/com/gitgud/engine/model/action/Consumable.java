package com.gitgud.engine.model.action;

public interface Consumable
{
    void consume();
    
    
    boolean isConsumed();
    
    
    void setConsumed(boolean isConsumed);
}
