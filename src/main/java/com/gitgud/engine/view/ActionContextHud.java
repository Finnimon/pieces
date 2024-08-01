package com.gitgud.engine.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * Provides a Hud for the {@link ActionContextRender}
 *
 * @param <ModelType> The type of {@link com.gitgud.engine.model.ActionAwaiterModel} to be rendered.
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public abstract class ActionContextHud<ModelType> extends Hud<ModelType>
{
    /**
     * Registered ActionChoice Nodes for later removal.
     */
    private final List<Node> choices;
    
    
    /**
     * Creates a new {@link ActionContextHud}.
     *
     * @param data The data to be rendered into a HUD.
     */
    public ActionContextHud(@NotNull ModelType data)
    {
        super(data);
        choices = new ArrayList<>();
    }
    
    
    @Override
    public void updateRender()
    {
        clearChoices();
    }
    
    
    /**
     * Removes all registered ActionChoiceRenders.
     */
    public void clearChoices()
    {
        getChildren().removeAll(choices);
        choices.clear();
    }
    
    
    /**
     * Adds a new ActionChoiceNode to the ActionContextHud and registers it for later removal.
     *
     * @param choiceNode The ActionChoiceNode to be added and registered.
     */
    public void addChoice(Node choiceNode)
    {
        registerChoice(choiceNode);
        getChildren().add(choiceNode);
        
        setAlignment(choiceNode, Pos.TOP_LEFT);
    }
    
    
    /**
     * Registers a new ActionChoiceNode for later removal.
     *
     * @param choiceNode The ActionChoiceNode to be registered.
     */
    public void registerChoice(Node choiceNode)
    {
        choices.add(choiceNode);
    }
    
    
}
