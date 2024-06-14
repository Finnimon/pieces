package com.gitgud.engine.model.gameObject.agent;

import com.gitgud.engine.model.gameObject.agent.attackDefenseLogic.Attacker;
import com.gitgud.engine.model.gameObject.agent.attackDefenseLogic.Defender;


public abstract class Fighter extends Agent implements Attacker, Defender
{
    public Fighter(String name, String description, String spriteUrl, boolean isFlying, int movementRange)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
    }
}
