package com.gitgud.utility.interfaces;

import com.gitgud.model.gameObjects.FightAgent;
import com.gitgud.model.mission.fight.Attack;
import com.gitgud.model.mission.fight.DamageType;
import com.gitgud.model.mission.fight.Defence;


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
        takeDamage(calculateDamage(attack));
    }
    
    
    private int calculateDamage(Attack attack)
    {
        Defence defence = getDefenceTo(attack.damageType());
        
        if (!attack.doesHit() || defence.doesEvade())
        {
            return 0;
        }
        
        int damage = attack.damage() - defence.defence();
        
        return damage;
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
