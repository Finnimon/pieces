package com.gitgud.model.gameObjects;

import com.gitgud.control.GridMovableController;
import com.gitgud.model.mission.GridMapContext;
import com.gitgud.utility.interfaces.GridMovable;


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
    
    
    @Override
    public GridMovableController getMovableController(GridMapContext gridMapContext)
    {
        return new GridMovableController(gridMapContext, this);
    }
    
    
}
