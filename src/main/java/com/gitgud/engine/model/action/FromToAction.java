package com.gitgud.engine.model.action;


public interface FromToAction<Awaiter extends ActionAwaiter, From, To> extends Action<Awaiter>
{
    From getFrom();
    
    
    To getTo();
}
