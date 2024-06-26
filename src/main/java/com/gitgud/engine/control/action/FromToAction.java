package com.gitgud.engine.control.action;


import com.gitgud.engine.control.ActionAwaitingController;
import javafx.geometry.Point2D;


public interface FromToAction<Awaiter extends ActionAwaitingController, Point extends Point2D> extends ToAction<Awaiter, Point>
{
    Point getFrom();
}
