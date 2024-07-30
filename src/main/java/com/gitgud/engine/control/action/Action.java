package com.gitgud.engine.control.action;


import com.gitgud.engine.control.ActionAwaitingController;


/**
 * <p>Base Interface for all Actions.
 * <p>Functional Interface for creating something to be chosen out of the options available to the player.
 *
 * @param <Awaiter> the type of the {@link ActionAwaitingController} this Action can be applied to
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public interface Action<Awaiter extends ActionAwaitingController>
{
    /**
     * Creates an empty action that does nothing
     *
     * @param <AAType>> the type of the {@link ActionAwaitingController} this Action can be applied to
     * @return an empty action
     */
    static <AAType extends ActionAwaitingController<?, ?, ?>> Action<AAType> rootReturn()
    {
        return ActionAwaitingController::showRootAction;
    }
    
    
    /**
     * Creates an empty action that does nothing
     *
     * @param <AAType> the type of the {@link ActionAwaitingController} this Action can be applied to
     * @return an empty action
     */
    static <AAType extends ActionAwaitingController> Action<AAType> empty()
    {
        return AAType ->
        {
            //do nothing
        };
    }
    
    
    /**
     * The Action takes effect on the {@link ActionAwaitingController} upon being called
     *
     * @param awaiter the {@link ActionAwaitingController} this Action will be enacted upon
     */
    void enAct(Awaiter awaiter);
}
