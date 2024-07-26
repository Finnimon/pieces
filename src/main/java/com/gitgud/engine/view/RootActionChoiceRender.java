package com.gitgud.engine.view;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


public class RootActionChoiceRender extends Group implements Render<RootActionChoice<?, ?, ?, ?>>
{
    private final AnchorPane anchorPane = new AnchorPane();
    
    
    public RootActionChoiceRender(RootActionChoice<?, ?, ?, ?> rootActionChoice)
    {
        render(rootActionChoice);
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
    
    
    @Override
    public void render(RootActionChoice<?, ?, ?, ?> data)
    {
        AnchorPane anchorPane = new AnchorPane();
        this.getChildren().add(anchorPane);
        
        
        double topAnchor = 0d;
        ObservableList<Node> children = anchorPane.getChildren();
        
        for (ActionChoice<?, ?, ?, ?> actionChoice : data.getChoices())
        {
            //topAnchor size is updated with each actionChoice
            topAnchor = renderActionChoice(actionChoice, children, topAnchor);
        }
    }
}
