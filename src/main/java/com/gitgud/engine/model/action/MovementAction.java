package com.gitgud.engine.model.action;

import com.gitgud.engine.model.action.types.Point;


public interface MovementAction<Num extends Number,T extends Point<Num>> extends FromToAction<T,T>
{
    @Override
    public default void enAct()
    {
        this.move(getFrom(), getTo());
    }
    
    
    public void move(T from, T to);
}
