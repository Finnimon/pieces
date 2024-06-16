package com.gitgud.engine.control;

public interface Ending
{
    /**
     * If isFinished returns true, then {@link #end()} will be called.
     * else returns false.
     *
     * @return if {@link #end()} has been called
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
    
    
    void end();
    
    
    boolean isFinished();
}
