package com.gitgud.engine.model.action;


import com.gitgud.engine.control.ActionAwaiter;


public interface Action<Awaiter extends ActionAwaiter>
{
    void enAct(Awaiter awaiter);
}
