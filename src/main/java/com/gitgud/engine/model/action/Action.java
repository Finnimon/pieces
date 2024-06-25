package com.gitgud.engine.model.action;


import com.gitgud.engine.control.ActionAwaitingController;


public interface Action<Awaiter extends ActionAwaitingController>
{
    void enAct(Awaiter awaiter);
    
    
    static<Awaiter extends ActionAwaitingController> Action<Awaiter> empty()
    {
        return new Action<Awaiter>()
        {
            @Override
            public void enAct(Awaiter awaiter)
            {
                //do nothing
            }
        };
    }
}
