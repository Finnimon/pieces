package com.gitgud.engine.model.gameObject.agent.attackDefenseLogic;

import com.gitgud.pieces.model.gameObjects.agents.FightAgent;


/**
 * All Objects using {@link Defence} to defend from {@link Attacker} must implement this interface.
 * implemented by {@link FightAgent}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface Defender extends Health
{
    public default void defend(Attack attack)
    {
        takeDamage(getDefenceTo(attack.damageType()).calculateDamage(attack));
    }
    
    
    private void takeDamage(int damage)
    {
        setHealth(getHealth() - damage);
    }
    
    
    public Defence getDefenceTo(DamageType damageType);
    
    
    public default boolean isDead()
    {
        return getHealth() <= 0;
    }
}
