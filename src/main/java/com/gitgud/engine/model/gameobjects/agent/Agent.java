package com.gitgud.engine.model.gameobjects.agent;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.GridMovable;


/**
 * Agents are the most basic controllable unit of the game. They can move on a {@link com.gitgud.engine.model.map.GridMap}
 *
 * @Owner: Finn L.
 * @Author: Finn L.
 * @Since: 12.06.2024
 * @Version: 2.0
 */
public abstract class Agent extends GameObject implements GridMovable
{
    private final boolean isFlying;
    
    
    private int movementRange;
    
    
    public Agent(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl);
        this.isFlying = isFlying;
        this.movementRange = movementRange;
    }
    
    
    @Override
    public int getMovementRange()
    {
        return movementRange;
    }
    
    
    public void setMovementRange(int movementRange)
    {
        this.movementRange = movementRange;
    }
    
    
    @Override
    public boolean isFlying()
    {
        return isFlying;
    }
    
    
}
