package com.gitgud.engine.view;

import javafx.collections.ObservableList;
import javafx.scene.Node;


/**
 * View Objects should usually implement this interface
 *
 * @param <ModelType>
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.1
 */
public interface Render<ModelType>
{
    /**
     * This method should always be called upon Object construction
     *
     * @param data the data to be rendered
     */
    void render(ModelType data);
    
    
    ObservableList<Node> getChildren();
    
}
