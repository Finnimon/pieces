package com.gitgud.engine.model.action;


import com.gitgud.engine.control.ActionAwaiterController;
import javafx.geometry.Point2D;


public interface FromToAction<Awaiter extends ActionAwaiterController, Point extends Point2D> extends ToAction<Awaiter, Point>
{
    Point getFrom();
}
