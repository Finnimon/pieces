package com.gitgud.engine.model.gameObject.agent.attackDefenseLogic;

import com.gitgud.pieces.model.gameObjects.agents.FightAgent;


/**
 * Objects that have a health stat.
 * implemented by {@link FightAgent}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface Health
{
    public int getHealth();
    
    
    public void setHealth(int health);
}
