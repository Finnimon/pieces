package com.gitgud.engine.model.action;

public interface FromToAction<Awaiter extends ActionAwaiter,From, To> extends Action<Awaiter>
{
    public From getFrom();
    public To getTo();
}
