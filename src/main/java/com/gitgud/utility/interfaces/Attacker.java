package com.gitgud.utility.interfaces;

import com.gitgud.model.gameObjects.FightAgent;
import com.gitgud.model.mission.fight.Attack;


/**
 * All Objects using {@link Attack} to attack {@link Defender} must implement this interface
 * implemented by {@link FightAgent}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public interface Attacker
{
    default boolean attack(Defender defender, float range)
    {
        defender.defend(createAttack(range));
        return defender.isDead();
    }
    
    
    Attack createAttack(float range);
    
}
