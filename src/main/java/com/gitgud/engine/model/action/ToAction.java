package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaiter;
import javafx.geometry.Point2D;


public interface ToAction<Awaiter extends ActionAwaiter, Point extends Point2D> extends Action<Awaiter>
{
    
    Point getTo();
}
