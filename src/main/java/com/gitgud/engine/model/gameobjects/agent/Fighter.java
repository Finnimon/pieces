package com.gitgud.engine.model.gameobjects.agent;

import com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic.Attacker;
import com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic.Defender;


public abstract class Fighter extends Agent implements Attacker, Defender
{
    public Fighter(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
    }
}
