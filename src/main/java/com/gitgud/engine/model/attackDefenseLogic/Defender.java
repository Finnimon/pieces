package com.gitgud.engine.model.attackDefenseLogic;

import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


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
    default void defend(Attack attack)
    {
        takeDamage(getDefenceTo(attack.damageType()).calculateDamage(attack));
    }
    
    
    private void takeDamage(int damage)
    {
        System.out.println("Defender.takeDamage(" + damage + ")");
        
        System.out.println(getHealth() +"-"+ damage);
        System.out.println(getDefenceTo(DamageType.PHYSICAL).defence());
        setHealth(getHealth() - damage);
    }
    
    
    Defence getDefenceTo(DamageType damageType);
    
    
    default boolean isDead()
    {
        return getHealth() <= 0;
    }
}
