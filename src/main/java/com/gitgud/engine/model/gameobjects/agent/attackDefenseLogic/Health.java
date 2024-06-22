package com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic;

import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


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
    int getHealth();
    
    
    void setHealth(int health);
}
