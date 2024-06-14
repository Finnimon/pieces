package com.gitgud.engine.model.action;



public interface Action<Awaiter extends ActionAwaiter>
{
    void enAct(Awaiter awaiter);
}
