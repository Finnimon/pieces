package com.gitgud.engine.model;

import com.github.ruediste.polymorphicGson.GsonPolymorph;


/**
 * Objects that can also be disApplied
 *
 * @param <T> The type of the object that can be applied to.
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 22.04.2024
 */
@GsonPolymorph
public interface DisApplicable<T> extends Applicable<T>
{
    /**
     * Undoes the {@link Applicable#apply(Object)}
     *
     * @param t The object to be disApplied from.
     * @return The object that was disApplied from.
     */
    T disApply(T t);
}
