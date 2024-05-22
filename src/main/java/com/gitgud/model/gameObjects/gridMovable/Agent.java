package com.gitgud.model.gameObjects.gridMovable;

import com.gitgud.model.gameObjects.GameObject;


public abstract class Agent extends GameObject implements GridMovable
{
    private final boolean isFlying;

    private int movementRange;
    
    
    public Agent (boolean isFlying, int movementRange)
    {
        this.isFlying = isFlying;

        this.movementRange = movementRange;
    }

    public Agent()
    {
        super();
        this.isFlying = false;
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
