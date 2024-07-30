package com.gitgud.engine.control.action;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.graph.Point2D;


/**
 * A Targeted {@link ToAction} which also has an Origin.
 *
 * @param <Awaiter> The type of the {@link ActionAwaitingController} this Action can be applied to
 * @param <Point>   The type of the {@link Point2D} this Action is targeted toward and originates from
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public interface FromToAction<Awaiter extends ActionAwaitingController, Point extends Point2D> extends ToAction<Awaiter, Point>
{
    /**
     * Gets The Origin of this Action
     *
     * @return the Origin of this Action
     */
    Point getFrom();
}
