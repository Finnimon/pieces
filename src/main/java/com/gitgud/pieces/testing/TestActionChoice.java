package com.gitgud.pieces.testing;

import com.gitgud.engine.control.ActionChoice;
import com.gitgud.engine.view.ActionChoiceRender;
import javafx.scene.Node;


public class TestActionChoice extends ActionChoice
{
    private final ActionChoiceRender node;
    
    
    public TestActionChoice()
    {
        super("Test", "Test");
        this.node = new ActionChoiceRender(this);
    }
    
    
    @Override
    public Node getNode()
    {
        return node;
    }
}
