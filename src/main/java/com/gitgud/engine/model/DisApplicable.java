package com.gitgud.engine.model;

import com.github.ruediste.polymorphicGson.GsonPolymorph;


/**
 * Objects that can also be disApplied
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 22.04.2024
 */
@GsonPolymorph
public interface DisApplicable<T> extends Applicable<T>
{
    T disApply(T t);
}
