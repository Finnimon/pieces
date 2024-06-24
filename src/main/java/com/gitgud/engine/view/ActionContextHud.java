package com.gitgud.engine.view;

import com.gitgud.engine.control.ActionChoice;
import com.gitgud.engine.control.StageController;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public  abstract class ActionContextHud<ModelType> extends Hud<ModelType>
{
    private final List<Node> choices;
    public ActionContextHud(ModelType data)
    {
        super(data);
        choices=new ArrayList<>();
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
