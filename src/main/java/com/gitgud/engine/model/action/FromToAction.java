package com.gitgud.engine.model.action;


import com.gitgud.engine.control.ActionAwaiter;
import javafx.geometry.Point2D;


public interface FromToAction<Awaiter extends ActionAwaiter, Point extends Point2D> extends ToAction<Awaiter, Point>
{
    Point getFrom();
}
