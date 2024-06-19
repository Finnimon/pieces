package com.gitgud.engine.model.action;

import javafx.geometry.Point2D;


public interface ToAction <Awaiter extends ActionAwaiter, Point extends Point2D> extends Action<Awaiter>
{

    Point getTo();
}
