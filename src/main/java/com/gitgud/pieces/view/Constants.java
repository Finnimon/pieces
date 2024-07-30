package com.gitgud.pieces.view;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


/**
 * Eine Klasse für Konstanten
 *
 * @author Delfina, Finn L.
 * @version 1.1
 * @Owner: Delfina
 * @since 15.07.2024
 */
public class Constants
{
    public static final double BUTTON_BREITE = 400;
    
    
    public static final double BUTTON_HOEHE = 90;
    
    
    public static final short DEFAULT_WIDTH = 50;
    
    
    public static final short MAP_DIMENSION = 12;
    
    
    public static final String MAP_PATH = "src/main/resources/com/gitgud/map/Map.csv";
    
    
    public static final Border BLACK_SQUARE_BORDER = new Border(new BorderStroke(Color.BLACK,
                                                                                 BorderStrokeStyle.SOLID,
                                                                                 CornerRadii.EMPTY,
                                                                                 BorderWidths.DEFAULT));
    
    //public static final String FONT_FILE_PATH = "src/main/resources/com/gitgud/fonts/Font.ttf";
    
    
    public static Background SEMI_TRANSPARENT_BACKGROUND = new Background(new BackgroundFill(new Color(1, 1, 1, 0.8),
                                                                                             CornerRadii.EMPTY,
                                                                                             Insets.EMPTY));
    
}

