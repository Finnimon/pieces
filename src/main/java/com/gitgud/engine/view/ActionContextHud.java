package com.gitgud.engine.view;

import com.gitgud.engine.control.ActionChoice;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.Iterator;


public  abstract class ActionContextHud<ModelType> extends Hud<ModelType>
{
    public ActionContextHud(ModelType data)
    {
        super(data);
    }
    
    
    @Override
    public void updateRender()
    {
        ObservableList<Node> children= getChildren();
        Iterator<Node> iterator = children.iterator();
        while (iterator.hasNext())
        {
            Node node = iterator.next();
            if (node instanceof RootActionChoiceRender||node instanceof ActionChoiceRender)
            {
                iterator.remove();
            }
        }
    }
}
