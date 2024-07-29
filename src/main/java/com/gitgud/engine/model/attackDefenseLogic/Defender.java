package com.gitgud.engine.model.attackDefenseLogic;

/**
 * All Objects using {@link Defence} to defend from {@link Attacker} must implement this interface.
 * implemented by {@link com.gitgud.engine.model.gameobjects.agent.Fighter}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface Defender extends Health
{
    default void defend(Attack attack)
    {
        takeDamage(getDefenceTo(attack.damageType()).calculateDamage(attack));
    }
    
    
    private void takeDamage(int damage)
    {
        setHealth(getHealth() - damage);
    }
    
    
    Defence getDefenceTo(DamageType damageType);
    
    
    default boolean isDead()
    {
        return getHealth() <= 0;
    }
}
