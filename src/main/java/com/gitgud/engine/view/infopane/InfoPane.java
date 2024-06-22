package com.gitgud.engine.view.infopane;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class InfoPane<Type> extends StackPane
{
    
    
    public static final Background DEFAULT_BACKGROUND = new Background(
            new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
    
    
    public static final Border DEFAULT_BORDER = new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    
    
    public InfoPane(Label label, Background background, Border border)
    {
        setBackground(background);
        setBorder(border);
        getChildren().add(label);
    }
    
    
    public InfoPane(String string)
    {
        this(new Label(string), DEFAULT_BACKGROUND, DEFAULT_BORDER);
    }
}
