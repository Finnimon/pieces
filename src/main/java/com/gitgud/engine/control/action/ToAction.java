package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.graph.Point2D;


public interface ToAction<Awaiter extends ActionAwaitingController, Point extends Point2D> extends Action<Awaiter>
{
    Point getTo();
}
