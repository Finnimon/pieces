package com.gitgud.engine.model;


import org.jetbrains.annotations.NotNull;


/**
 * Objects that can be applied.
 *
 * @param <T> The type of the object that can be applied to.
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 22.04.2024
 */

public interface Applicable<T>
{
    /**
     * <p>Applies the {@link Applicable} to the {@link T}.
     * <p>Returns the {@link T} for the case, that only a copy could be made and changes.
     *
     * @param t The {@link T} to apply the {@link Applicable} to.
     * @return The {@link T} after application.
     */
    T apply(@NotNull T t);
}
