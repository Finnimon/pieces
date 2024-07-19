package com.gitgud.pieces.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.MalformedURLException;


/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 18.06.2024
 */
public class STale
{
    
    public static Scene createTaleScene(Stage stage)
    {
        String difficultySceneBackgroundPath = "src\\main\\resources\\com\\gitgud\\backgroundImages\\Wallpaper.png";
        VBox root = GuiUtils.createVBox(difficultySceneBackgroundPath);
        Scene taleScene = new Scene(root);
        stage.setScene(taleScene);
        
        Text difficultyMenueText = new Text("Schwierigkeitsgrad");
        difficultyMenueText.setFont(Font.font("Lato", FontWeight.MEDIUM, 24)); // Schriftart und Größe festlegen
        root.getChildren().add(difficultyMenueText);
        
        //erstellen der Szene mit den buttons etc
        Button[] buttonsArray = new Button[4];
        for (int i = 0; i < buttonsArray.length; i++)
        {
            buttonsArray[i] = new Button("Mission " + (i + 1));
        }
        Font customFont = null;
        try
        {
            customFont = GuiUtils.loadFont(20);
        }
        catch (MalformedURLException e)
        {
            System.err.println("Schriftart konnte nicht geladen werden: " + e.getMessage());
        }
        
        // Größe und Schriftart für alle Buttons festlegen
        if (customFont != null)
        {
            for (Button buttonT : buttonsArray)
            {
                GuiUtils.setButtonTrait(buttonT, customFont);
            }
        }
        else
        {
            // Fallback auf Standard-Schriftart
            Font defaultFont = Font.font("Verdana", 20);
            for (Button buttonT : buttonsArray)
            {
                GuiUtils.setButtonTrait(buttonT, defaultFont);
            }
        }
        
        
        buttonsArray[0].setOnAction(e -> stage.setScene(SReborn.createRebornScene(stage)));
        buttonsArray[1].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        buttonsArray[2].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        buttonsArray[3].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        
        
        root.getChildren().addAll(buttonsArray);
        return taleScene;
    }
    
}

