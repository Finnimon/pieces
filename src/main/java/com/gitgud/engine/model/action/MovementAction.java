package com.gitgud.engine.model.action;


import com.gitgud.engine.control.ActionAwaiter;
import javafx.geometry.Point2D;


public interface MovementAction<Awaiter extends ActionAwaiter, T extends Point2D> extends FromToAction<Awaiter, T, T>
{
}
