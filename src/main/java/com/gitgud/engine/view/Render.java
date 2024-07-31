package com.gitgud.engine.view;

import javafx.collections.ObservableList;
import javafx.scene.Node;


/**
 * View Objects should usually implement this interface and also Extend {@link javafx.scene.Parent}
 *
 * @param <ModelType>
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public interface Render<ModelType>
{
    /**
     * <p>This method should always be called upon Object construction
     * <p>This method is used to render the model into a Node
     *
     * @param model the model to be rendered
     */
    void render(ModelType model);
    
    
    /**
     * Getter for the Render's children
     *
     * @return the Render's children
     */
    ObservableList<Node> getChildren();
}
