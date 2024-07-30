package com.gitgud.engine.control;

/**
 * This Interface specifies {@link Advancing}s that should be ended if they are finished.
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
        if (!isFinished())
        {
            return false;
        }
        
        end();
        return true;
    }
    
    
    /**
     * Determines if the {@link Ending} has finished and should be ended.
     * @return whether the {@link Ending} has finished and should be ended.
     */
    boolean isFinished();
    
    
    /**
     * Ends the current {@link Ending}.
     */
    void end();
}
