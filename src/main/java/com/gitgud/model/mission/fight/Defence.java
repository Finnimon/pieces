package com.gitgud.model.mission.fight;

import com.gitgud.utility.Core;


/**
 * A Defence to interact with {@link Attack} objects inside {@link Defender}.
 *
 * @param defence       The amount of damage this Defence blocks
 * @param evasionChance Value between 0 and 1. Is the chance of the {@link Attack} being evaded
 * @param damageType    The type of damage this defence targets
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 * @see Defender#defend(Attack)
 */
public record Defence(int defence, float evasionChance, DamageType damageType)
{
    /**
     * Determines if the Attack hits. Has a Chance of {@link #evasionChance()}.
     *
     * @return true if the {@link Attack} is evaded.
     * @Owner: Finn L.
     * @Author: Finn L.
     * @Since: 16.04.2024
     * @Version: 1.0
     */
    public boolean doesEvade()
    {
        return Core.roll(evasionChance());
    }
}
