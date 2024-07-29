package com.gitgud.engine.model.gameobjects.agent;

import com.gitgud.engine.model.attackDefenseLogic.Attacker;
import com.gitgud.engine.model.attackDefenseLogic.Defender;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;

import java.util.Collection;


public abstract class Fighter extends Agent implements Attacker, Defender
{
    public Fighter(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
    }
    
    
    public abstract Collection<Tile> findAttackableTiles(GridMap gridMap, Tile position);
}
