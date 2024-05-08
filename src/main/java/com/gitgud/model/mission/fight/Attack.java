package com.gitgud.model.mission.fight;


import static com.gitgud.utility.Core.roll;


/**
 * An Attack to send to an {@link Defender}
 *
 * @param damage     The amount of damage the Attack does.
 * @param accuracy   Value between 0 and 1. Is the chance of the Attack hitting.
 * @param damageType The type of damage the Attack does.
 * @Owner: Finn L.
 * @author Finn L.
 * @since  16.04.2024
 * @Version: 1.0
 * @see Defender#defend(Attack)
 */
public record Attack(int damage, float accuracy, DamageType damageType)
{
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
