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
    /**
     * Whether the agent can fly.
     * @see GridMovable
     */
    private final boolean isFlying;
    
    
    /**
     * The movement range of the agent.
     */
    private final SimpleIntegerProperty movementRangeProperty;
    
    
    /**
     * Calls the constructor of {@link GameObject#GameObject(String, String, String)} and assigns values to {@link #isFlying} and {@link #movementRangeProperty}.
     * @see GameObject#GameObject(String, String, String)  GameObject
     * @param name The name of the agent.
     * @param description The description of the agent.
     * @param spriteFilePath The sprite file path of the agent.
     * @param isFlying Whether the agent can fly.
     * @param movementRange The movement range of the agent.
     */
    public Agent(String name, String description, String spriteFilePath, boolean isFlying, int movementRange)
    {
        super(name, description, spriteFilePath);
        this.isFlying = isFlying;
        this.movementRangeProperty = new SimpleIntegerProperty(movementRange);
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
    
    
}
