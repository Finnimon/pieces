package com.gitgud.pieces.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 12.06.2024
 */

public class SWrought
{
    
    public static Scene createWroughtScene(Stage stage)
    {
        
        Text WroughtMenueText = new Text("Schmiede");
        VBox root = new VBox();
        Scene scene = new Scene(root);
        WroughtMenueText.setFont(Font.font("Verdana", FontWeight.MEDIUM, 30)); // Schriftart und Größe festlegen
        WroughtMenueText.setFill(Color.BISQUE); //Farbe des texts festlegen
        root.getChildren().add(WroughtMenueText);
        
        
        //Erstellen der Szene mit den buttons etc
        Button[] buttonsArray = new Button[2];
        buttonsArray[0] = new Button("Artefakte verbessern");
        buttonsArray[1] = new Button("Artefakte schmieden");
        
        //Größe und Schriftart für jeweils alle Buttons festlegen
        for (Button buttonD : buttonsArray)
        {
            GuiUtils.setButtonTrait(buttonD);
        }
        
        buttonsArray[0].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        buttonsArray[1].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        
        
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(buttonsArray);
        return scene;
    }
    
}


