package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.graph.Point2D;


/**
 * An Action that can be applied to a {@link ActionAwaitingController} and is targeted toward a {@link Point2D}.
 *
 * @param <AaType> The type of the {@link ActionAwaitingController} this Action can be applied to
 * @param <Point>  The type of the {@link Point2D} this Action is targeted toward
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public interface ToAction<AaType extends ActionAwaitingController, Point extends Point2D> extends Action<AaType>
{
    /**
     * Gets the Target of this action
     *
     * @return the target of this action
     */
    Point getTo();
}
