package com.gitgud.pieces.view.sUnused;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.MalformedURLException;


/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 11.05.2024
 */

public class SMainMenue
{
    
    private static Font customFont;
    
    
    public static Scene createMainMenueScene(Stage stage)
    {
        
        VBox root = GuiUtils.createVBox("src\\main\\resources\\com\\gitgud\\backgroundImages\\Wallpaper.png");
        Scene MainMenueScene = new Scene(root, 800, 600); //alle Knoten als Inhalt der Szene festgelegt
        stage.setScene(MainMenueScene);
        
        Label hauptmenueText = null;
        Font customFont = null;
        try
        {
            
            customFont = GuiUtils.loadFont(25);
            
            // Label für Hauptmenü erstellen und Schriftart setzen
            hauptmenueText = new Label("Hauptmenü");
            hauptmenueText.setFont(customFont);
            hauptmenueText.setTextFill(Color.LIGHTSLATEGRAY);
        }
        catch (MalformedURLException e)
        {
            System.out.println(e.getMessage());
            
        }
        
        //erstellen der Szene mit den buttons etc
        Button[] buttonsArray = new Button[5];
        buttonsArray[0] = new Button("Fortfahren");
        buttonsArray[1] = new Button("Spiel laden");
        buttonsArray[2] = new Button("Einstellungen");
        buttonsArray[3] = new Button("Spiel beenden");
        buttonsArray[4] = new Button("Arena");
        
        
        if (customFont != null)
        {
            GuiUtils.setButtonTraits(buttonsArray, customFont);
        }
        
        //buttonsArray[0].setOnAction(e -> stage.setScene(oldGameScene)); TODO: zurück ins alte Spiel
        buttonsArray[1].setOnAction(e -> stage.setScene(SDifficulty.createDifficultyScene(stage)));
        //buttonsArray[2].setOnAction(e -> stage.setScene(settings)) TODO: brauchen wir überhaupt Settings?
        // Anwendung komplett beenden
        buttonsArray[3].setOnAction(e -> Platform.exit());
        buttonsArray[4].setOnAction(e -> stage.setScene(SArena.createArenaScene(stage)));
        
        root.getChildren().add(hauptmenueText);
        //getChildren()-Methode gibt eine Liste der direkten untergeordneten Knoten des aktuellen Knotens zurück
        root.getChildren().addAll(buttonsArray);
        
        
        stage.setOnCloseRequest(event -> windowCloseRequest(event, stage));
        
        return MainMenueScene;
    }
    
    
    private static void windowCloseRequest(WindowEvent event, Stage stage)
    {
        event.consume();
        stage.setScene(createMainMenueScene(stage));
    }
}
