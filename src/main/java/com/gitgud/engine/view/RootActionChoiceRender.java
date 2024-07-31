package com.gitgud.engine.view;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class RootActionChoiceRender extends Group implements Render<RootActionChoice<?, ?, ?, ?>>
{
    private final AnchorPane anchorPane = new AnchorPane();
    
    
    public RootActionChoiceRender(RootActionChoice<?, ?, ?, ?> rootActionChoice)
    {
        render(rootActionChoice);
    }
    
    
    @Override
    public void render(RootActionChoice<?, ?, ?, ?> model)
    {
        AnchorPane anchorPane = new AnchorPane();
        this.getChildren().add(anchorPane);
        
        
        double topAnchor = 0d;
        ObservableList<Node> children = anchorPane.getChildren();
        
        for (ActionChoice<?, ?, ?, ?> actionChoice : model.getChildren())
        {
            //topAnchor size is updated with each actionChoice
            topAnchor = renderActionChoice(actionChoice, children, topAnchor);
        }
    }
    
    
    private static double renderActionChoice(ActionChoice<?, ?, ?, ?> actionChoice, ObservableList<Node> children,
                                             double spacer)
    {
        ActionChoiceRender render = actionChoice.getNode();
        children.add(render);
        
        AnchorPane.setTopAnchor(render, spacer);
        
        spacer += ActionChoiceRender.SIZE * 2;
        
        
        return spacer;
    }
}
