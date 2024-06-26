package com.gitgud.engine.control.action.types;

public interface Applicable<T>
{
    T apply(T t);
}
