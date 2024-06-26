package com.gitgud.engine.control.action;


import com.gitgud.engine.control.ActionAwaitingController;


public interface Action<Awaiter extends ActionAwaitingController>
{
    static <AAType extends ActionAwaitingController> Action<AAType> rootReturn()
    {
        return new Action<AAType>()
        {
            @Override
            public void enAct(AAType awaiter)
            {
                awaiter.getActionChoice().select();
            }
        };
    }
    
    
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
