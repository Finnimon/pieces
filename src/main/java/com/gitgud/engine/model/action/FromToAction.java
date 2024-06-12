package com.gitgud.engine.model.action;

public interface FromToAction<F,T> extends Action
{
    public F getFrom();
    public T getTo();
}
