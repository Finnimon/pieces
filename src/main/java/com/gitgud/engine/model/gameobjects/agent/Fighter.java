package com.gitgud.engine.model.gameobjects.agent;

import com.gitgud.engine.model.attackDefenseLogic.Attacker;
import com.gitgud.engine.model.attackDefenseLogic.Defender;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;

import java.util.Collection;


/**
 * An Agent that also offers attack and defense capabilities.
 *
 * @Owner: Finn L.
 * @Author: Finn L.
 * @Since: 12.06.2024
 * @Version: 1.0
 */

public abstract class Fighter extends Agent implements Attacker, Defender
{
    public Fighter(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
    }
    
    
    public abstract Collection<Tile> findAttackableTiles(GridMap gridMap, Tile position);
}
