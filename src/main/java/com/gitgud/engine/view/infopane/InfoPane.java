package com.gitgud.engine.view.infopane;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class InfoPane<Type> extends StackPane
{
    public static final Border DEFAULT_BORDER = new Border(new BorderStroke(Color.BLACK,
                                                                            BorderStrokeStyle.SOLID,
                                                                            CornerRadii.EMPTY,
                                                                            BorderWidths.DEFAULT));
    
    
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(1, 1, 1, 0.8);
    
    
    public static final Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(DEFAULT_BACKGROUND_COLOR,
                                                                                          CornerRadii.EMPTY,
                                                                                          Insets.EMPTY));
    
    
    private final Label label;
    
    
    public InfoPane(Label label, Background background, Border border)
    {
        setBackground(background);
        setBorder(border);
        getChildren().add(label);
        this.label = label;
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }
    
    
    public InfoPane(String string)
    {
        this(new Label(string), DEFAULT_BACKGROUND, DEFAULT_BORDER);
    }
    
    
    protected Label getLabel()
    {
        return label;
    }
}
