package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaiter;


public interface FromToAction<Awaiter extends ActionAwaiter, From, To> extends Action<Awaiter>
{
    From getFrom();
    
    
    To getTo();
}
