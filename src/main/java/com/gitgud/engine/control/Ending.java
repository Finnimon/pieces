package com.gitgud.engine.control;

import javafx.beans.property.SimpleBooleanProperty;


/**
 * This Interface specifies {@link Advancing}s that should be ended if they are finished.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public interface Ending extends Advancing
{
    /**
     * If isFinished returns true, then {@link #end()} will be called.
     * else returns false.
     *
     * @return whether {@link #end()} has been called
     */
    default boolean tryEnd()
    {
        isFinishedProperty().setValue(isFinished());
        if (!isFinishedProperty().get())
        {
            return false;
        }
        
        end();
        return true;
    }
    
    
    /**
     * Observable value that gets updated with every {@link #tryEnd()}.
     *
     * @return An observable that is regularly updated to create bindings.
     */
    SimpleBooleanProperty isFinishedProperty();
    
    
    /**
     * <p>Determines if the {@link Ending} has finished and should be ended.
     * <p>Do not return {@link #isFinishedProperty()} unless you update its value elsewhere.
     *
     * @return whether the {@link Ending} has finished and should be ended.
     */
    boolean isFinished();
    
    
    /**
     * Ends the current {@link Ending}.
     */
    void end();
}
