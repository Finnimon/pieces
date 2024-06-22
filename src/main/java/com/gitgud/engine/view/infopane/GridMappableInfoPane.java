package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.GridMappable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class GridMappableInfoPane<Type extends GridMappable> extends StackPane
{
    
    public GridMappableInfoPane(Type gridMappable)
    {
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Label nameLabel=new Label(gridMappable.name()+"\n\r"+gridMappable.description());
        this.getChildren().addAll(nameLabel);
//        setMaxSize(nameLabel.getWidth(), nameLabel.getHeight());
    }
}
