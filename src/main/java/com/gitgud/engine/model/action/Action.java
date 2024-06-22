package com.gitgud.engine.model.action;


import com.gitgud.engine.control.ActionAwaiterController;


public interface Action<Awaiter extends ActionAwaiterController>
{
    void enAct(Awaiter awaiter);
}
