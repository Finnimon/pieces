package com.gitgud.engine.view;

import javafx.geometry.Pos;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;


public abstract class ActionContextHud<ModelType> extends Hud<ModelType>
{
    private final List<Node> choices;
    
    
    public ActionContextHud(ModelType data)
    {
        super(data);
        choices = new ArrayList<>();
    }
    
    
    @Override
    public void updateRender()
    {
        clearChoices();
    }
    
    
    public void addChoice(Node choiceNode)
    {
        registerChoice(choiceNode);
        getChildren().add(choiceNode);
        
        setAlignment(choiceNode, Pos.TOP_LEFT);
    }
    
    
    public void registerChoice(Node choiceNode)
    {
        choices.add(choiceNode);
    }
    
    
    public void clearChoices()
    {
        getChildren().removeAll(choices);
        choices.clear();
    }
    
    
}
