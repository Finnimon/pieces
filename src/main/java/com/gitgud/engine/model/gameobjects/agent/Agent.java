package com.gitgud.engine.model.gameobjects.agent;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.GridMovable;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Agents are the most basic controllable unit of the game. They can move on a
 * {@link com.gitgud.engine.model.map.GridMap}
 *
 * @Owner: Finn L.
 * @Author: Finn L.
 * @Since: 12.06.2024
 * @Version: 2.0
 */
public abstract class Agent extends GameObject implements GridMovable
{
    private final boolean isFlying;
    
    
    private final SimpleIntegerProperty movementRangeProperty;
    
    
    public Agent(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl);
        this.isFlying = isFlying;
        this.movementRangeProperty = new SimpleIntegerProperty(movementRange);
    }
    
    
    @Override
    public int getMovementRange()
    {
        return movementRangeProperty.getValue();
    }
    
    
    @Override
    public SimpleIntegerProperty movementRangeProperty()
    {
        return movementRangeProperty;
    }
    
    
    @Override
    public boolean isFlying()
    {
        return isFlying;
    }
    
    
    public void setMovementRange(int movementRange)
    {
        movementRangeProperty.setValue(movementRange);
    }
    
    
}
