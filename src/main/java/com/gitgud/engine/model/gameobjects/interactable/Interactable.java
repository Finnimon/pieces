package com.gitgud.engine.model.gameobjects.interactable;


import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GridMappable;


/**
 * Objects that can be interacted with on a {@link com.gitgud.engine.model.map.GridMap}.
 * @param <ActionAwaitingType>
 */
public interface Interactable<ActionAwaitingType extends ActionAwaitingController> extends GridMappable
{
    /**
     * Interacts with this object.
     * @param actionAwaiter the action awaiter controlling the interaction.
     */
    void interact(ActionAwaitingType actionAwaiter);
}
