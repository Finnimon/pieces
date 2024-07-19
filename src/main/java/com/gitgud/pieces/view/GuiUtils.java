package com.gitgud.pieces.view;

import com.gitgud.engine.model.gameobjects.Sprite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.MalformedURLException;


/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 18 .05.2024
 * eine Utility Klasse, die Methoden zum Erstellen und Konfigurieren von Buttons und Layouts enthaelt
 */

public class GuiUtils
{
    
    public static final String FONT_FILE_PATH = "src/main/resources/com/gitgud/fonts/Font.ttf";
    
    
    //Methode zur Festlegung der Größe und Schriftart für einen Button
    public static void setButtonTrait(Button button, Font font)
    {
        button.setPrefWidth(Constants.BUTTON_BREITE);
        button.setPrefHeight(Constants.BUTTON_HOEHE);
        button.setFont(font);
    }
    
    
    public static void setButtonTraits(Button[] buttons, Font font)
    {
        for (Button button : buttons)
        {
            setButtonTrait(button, font);
        }
    }
    
    
    public static Font loadFont(double size) throws MalformedURLException
    {
        
        try
        {
            return Font.loadFont(Sprite.urlFromFilePath(FONT_FILE_PATH), size);
            
        }
        catch (NullPointerException e)
        {
            throw new MalformedURLException("file invalid");
        }
    }
    
    
    public static VBox createVBox(String backgroundImagePath)
    {
        VBox vbox = new VBox(); //Layout, in dem die untergeordneten Knoten vertikal gestapelt sind
        vbox.setPadding(new Insets(100));
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);
        
        // Hintergrundbild festlegen
        Image backgroundImage = null;
        try
        {
            backgroundImage = new Image(Sprite.urlFromFilePath(backgroundImagePath));
        }
        catch (MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true,
                                                           false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                                                         backgroundSize);
        vbox.setBackground(new Background(background));
        
        return vbox;
    }
    
    
}
