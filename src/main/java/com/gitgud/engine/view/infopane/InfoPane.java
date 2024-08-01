package com.gitgud.engine.view.infopane;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


/**
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class InfoPane extends StackPane
{
    /**
     * The default border around an InfoPane.
     */
    protected static final Border DEFAULT_BORDER = new Border(new BorderStroke(Color.BLACK,
                                                                               BorderStrokeStyle.SOLID,
                                                                               CornerRadii.EMPTY,
                                                                               BorderWidths.DEFAULT));
    
    
    /**
     * The default background color of an InfoPane.
     */
    protected static final Color DEFAULT_BACKGROUND_COLOR = new Color(1, 1, 1, 0.8);
    
    
    /**
     * The default background of an InfoPane.
     */
    protected static final Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(DEFAULT_BACKGROUND_COLOR,
                                                                                             CornerRadii.EMPTY,
                                                                                             Insets.EMPTY));
    
    
    /**
     * The TextLabel of an InfoPane.
     */
    private final Label label;
    
    
    /**
     * Defaults to {@link #InfoPane(Label, Background, Border)} with {@link #DEFAULT_BACKGROUND},
     * {@link #DEFAULT_BORDER}.
     *
     * @param string The TextLabel content for the InfoPane.
     */
    public InfoPane(String string)
    {
        this(new Label(string), DEFAULT_BACKGROUND, DEFAULT_BORDER);
    }
    
    
    /**
     * Default constructor that sets all values.
     *
     * @param label      The TextLabel for the InfoPane.
     * @param background The Background for the InfoPane.
     * @param border     The Border for the InfoPane.
     */
    public InfoPane(Label label, Background background, Border border)
    {
        setBackground(background);
        setBorder(border);
        getChildren().add(label);
        this.label = label;
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }
    
    
    /**
     * <p>Getter for the TextLabel of the InfoPane.
     * <p>To be used by subclasses for adding more info.
     *
     * @return The TextLabel of the InfoPane.
     */
    protected final Label getLabel()
    {
        return label;
    }
}
