package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaitingController;
import javafx.geometry.Point2D;


public interface ToAction<Awaiter extends ActionAwaitingController, Point extends Point2D> extends Action<Awaiter>
{
    
    Point getTo();
}
