package com.gitgud.engine.control;

/**
 * This interface defines a method that can be called to start a new situation in the game and change the scene.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
@FunctionalInterface
public interface Startable
{
    /**
     * Start the new situation and changes the scene.
     */
    void start();
}
