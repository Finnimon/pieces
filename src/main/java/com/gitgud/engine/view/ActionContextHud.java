package com.gitgud.engine.view;

import javafx.scene.Group;
import javafx.scene.Node;


public  abstract class ActionContextHud<ModelType> extends Hud<ModelType>
{
    private final Group actionChoiceRenderGroup =new Group();
    public ActionContextHud(ModelType data)
    {
        super(data);
        getChildren().add(actionChoiceRenderGroup);
    }
    
    
    public void clearActionChoiceRenders()
    {
        actionChoiceRenderGroup.getChildren().clear();
    }
    
    public void addActionChoiceRender(Node node)
    {
        actionChoiceRenderGroup.getChildren().add(node);
    }
    
}
