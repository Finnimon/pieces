package com.gitgud.engine.model.attackDefenseLogic;

import javafx.beans.property.SimpleIntegerProperty;
import org.jetbrains.annotations.NotNull;


/**
 * All Objects using {@link Attack} to attack {@link Defender} must implement this interface
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 * @see Attack
 * @see Defender
 * @see com.gitgud.engine.model.gameobjects.agent.Fighter
 */
public interface Attacker
{
    /**
     * Attack the given {@link Defender}.
     *
     * @param defender The {@link Defender} to attack.
     * @param distance The distance between Defender and Attacker. Useful to determine if the attack is ranged.
     */
    default void attack(@NotNull Defender defender, float distance)
    {
        createAttack(distance).apply(defender);
    }
    
    
    /**
     * Create an {@link Attack} with the given distance.
     *
     * @param distance The distance between Defender and Attacker. Useful to determine if the attack is ranged.
     * @return The created {@link Attack}.
     */
    Attack createAttack(float distance);
    
    
    /**
     * Gets the amount of ranged attacks the Attacker has remaining.
     *
     * @return The amount of ranged attacks the Attacker has remaining.
     */
    default int getRemainingRangedAttacks()
    {
        return remainingRangedAttacksProperty().get();
    }
    
    
    /**
     * Sets the amount of ranged attacks the Attacker has remaining.
     *
     * @param remainingRangedAttacks the new amount of ranged attacks the Attacker has remaining.
     */
    default void setRemainingRangedAttacks(int remainingRangedAttacks)
    {
        remainingRangedAttacksProperty().set(remainingRangedAttacks);
    }
    
    
    /**
     * Getter for the remaining ranged attacks property.
     *
     * @return the remaining ranged attacks property
     */
    SimpleIntegerProperty remainingRangedAttacksProperty();
    
}
