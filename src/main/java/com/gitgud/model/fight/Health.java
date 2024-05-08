package com.gitgud.model.fight;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;


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
