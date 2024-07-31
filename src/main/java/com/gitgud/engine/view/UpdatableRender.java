package com.gitgud.engine.view;

/**
 * Render that can be updated to reflect changes in the previously rendered Data.
 *
 * @param <DataType> The DataType to be rendered
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public interface UpdatableRender<DataType> extends Render<DataType>
{
    /**
     * Update the Render to reflect changes in {@link #getData()}
     */
    void updateRender();
    
    
    /**
     * Getter for the data in order to be able to rerender it.
     *
     * @return The data this render should render and update its render to.
     */
    DataType getData();
}
