package com.gitgud.engine.view;

/**
 * Render that can be updated to reflect changes in the Model
 * @param <ModelType>
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public interface UpdatableRender<ModelType> extends Render<ModelType>
{
    void updateRender();
    
    
    ModelType getData();
}
