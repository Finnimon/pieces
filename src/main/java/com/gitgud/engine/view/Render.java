package com.gitgud.engine.view;

/**
 * View Objects should usually implement this interface
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.1
 * @param <DataType>
 */
public interface Render<DataType>
{
    /**
     * This method should always be called upon Object construction
     * @param data the data to be rendered
     *
     */
    void render(DataType data);
}
