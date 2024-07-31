package com.gitgud.engine.view;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


/**
 * Render for the RootActionChoice {@link com.gitgud.engine.control.actionChoice.RootActionChoice}.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 * TODO: 30.06.2024 As of yet this class is still quite problematic. Should be replaced with a ScrollPane with VBox
 */
public class RootActionChoiceRender extends Group implements Render<RootActionChoice<?, ?, ?, ?>>
{
    private final AnchorPane anchorPane = new AnchorPane();
    
    
    /**
     * Constructs and renders with the given {@link RootActionChoice}.
     *
     * @param rootActionChoice The RootActionChoice to render.
     */
    public RootActionChoiceRender(RootActionChoice<?, ?, ?, ?> rootActionChoice)
    {
        render(rootActionChoice);
    }
    
    
    @Override
    public void render(RootActionChoice<?, ?, ?, ?> model)
    {
        AnchorPane anchorPane = new AnchorPane();
        this.getChildren().add(anchorPane);
        
        ObservableList<Node> children = anchorPane.getChildren();
        double topAnchor = 0d;
        for (ActionChoice<?, ?, ?, ?> actionChoice : model.getChildren())
        {
            //topAnchor size is updated with each actionChoice
            topAnchor = renderActionChoice(actionChoice, children, topAnchor);
        }
    }
    
    
    /**
     * Renders the next ActionChoice's {@link ActionChoiceRender} and offsets it to the bottom. Returns the next offset.
     *
     * @param actionChoice The ActionChoice to be rendered.
     * @param children     The children to add the ActionChoiceRender to.
     * @param topAnchor    The topAnchor of {@code actionChoice}.
     * @return The next topAnchor.
     */
    private static double renderActionChoice(ActionChoice<?, ?, ?, ?> actionChoice, ObservableList<Node> children,
                                             double topAnchor)
    {
        ActionChoiceRender render = actionChoice.getNode();
        children.add(render);
        
        AnchorPane.setTopAnchor(render, topAnchor);
        
        topAnchor += ActionChoiceRender.SIZE * 2;
        
        
        return topAnchor;
    }
}
