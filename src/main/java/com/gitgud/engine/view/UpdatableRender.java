package com.gitgud.engine.view;

/**
 * Render that can be updated to reflect changes in the Model
 * @param <ModelType>
 */
public interface UpdatableRender<ModelType> extends Render<ModelType>
{
    void updateRender();
    
    
    ModelType getModel();
}
