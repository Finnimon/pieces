package com.gitgud.engine.model.gameObject.agent.attackDefenseLogic;

/**
 * All Objects using {@link Attack} to attack {@link Defender} must implement this interface
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface Attacker
{
    default boolean attack(Defender defender, float distance)
    {
        defender.defend(createAttack(distance));
        return defender.isDead();
    }
    
    
    Attack createAttack(float distance);
}
