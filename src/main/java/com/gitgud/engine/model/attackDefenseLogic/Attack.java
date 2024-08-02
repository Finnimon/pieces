package com.gitgud.engine.model.attackDefenseLogic;


import org.jetbrains.annotations.NotNull;

import static com.gitgud.pieces.utility.Core.roll;


/**
 * An Attack to send to an {@link Defender}
 *
 * @param damage     The amount of damage the Attack does.
 * @param accuracy   Value between 0 and 1. Is the chance of the Attack hitting.
 * @param damageType The type of damage the Attack does.
 * @author Finn L.
 * @Owner: Finn L.
 * @Version: 1.0
 * @see Defender#defend(Attack)
 * @since 16.04.2024
 */
public record Attack(int damage, double accuracy, DamageType damageType)
{
    public Defender apply(@NotNull Defender fighter)
    {
        if (!doesHit())
        {
            return fighter;
        }
        fighter.defend(this);
        return fighter;
    }
    
    
    /**
     * Determines if the Attack hits. Has a Chance of {@link #accuracy}.
     *
     * @return true if the Attack hits.
     * @Owner: Finn L.
     * @Author: Finn L.
     * @Since: 16.04.2024
     * @Version: 1.0
     */
    public boolean doesHit()
    {
        return roll(accuracy);
    }
}
