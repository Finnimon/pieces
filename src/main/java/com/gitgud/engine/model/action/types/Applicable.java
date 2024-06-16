package com.gitgud.engine.model.action.types;

public interface Applicable<T>
{
    T apply(T t);
}
