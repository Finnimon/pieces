package com.gitgud.model.fight;

import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;


/**
 * All Objects using {@link Defence} to defend from {@link Attacker} must implement this interface.
 * implemented by {@link FightAgentFL}
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
