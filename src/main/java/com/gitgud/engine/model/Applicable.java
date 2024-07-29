package com.gitgud.engine.model;


/**
 * Objects that can be applied
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 22.04.2024
 */

public interface Applicable<T>
{
    T apply(T t);
}
