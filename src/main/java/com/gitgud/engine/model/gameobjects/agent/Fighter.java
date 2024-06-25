package com.gitgud.engine.model.gameobjects.agent;

import com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic.Attacker;
import com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic.Defender;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

import java.util.Collection;
import java.util.List;


public abstract class Fighter extends Agent implements Attacker, Defender
{
    public Fighter(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
    }
    
    
    public abstract Collection<Tile> findAttackableTiles(GridMap gridMap, Tile position);
    
}
