package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaiterController;
import javafx.geometry.Point2D;


public interface ToAction<Awaiter extends ActionAwaiterController, Point extends Point2D> extends Action<Awaiter>
{
    
    Point getTo();
}
