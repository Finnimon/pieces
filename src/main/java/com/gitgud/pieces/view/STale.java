package com.gitgud.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 18.06.2024
 */
public class STale {

    public static Scene createTaleScene(Stage stage) {
        String difficultySceneBackgroundPath = "src\\main\\resources\\com\\gitgud\\backgroundImages\\Wallpaper.png";
        VBox root = GuiUtils.createVBox(difficultySceneBackgroundPath);
        Scene taleScene = new Scene(root);
        stage.setScene(taleScene);

        Text difficultyMenueText = new Text("Schwierigkeitsgrad");
        difficultyMenueText.setFont(Font.font("Lato", FontWeight.MEDIUM, 24)); // Schriftart und Größe festlegen
        root.getChildren().add(difficultyMenueText);

        //erstellen der Szene mit den buttons etc
        Button[] buttonsArray = new Button[4];
        for (int i = 0; i < buttonsArray.length; i++) {
            buttonsArray[i] = new Button("Mission " + (i + 1));
        }

        //Größe und Schriftart für jeweils alle Buttons festlegen
        for (Button buttonT : buttonsArray) {
            GuiUtils.setButtonTrait(buttonT);
        }

        buttonsArray[0].setOnAction(e -> stage.setScene(SReborn.createRebornScene(stage)));
        buttonsArray[1].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        buttonsArray[2].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        buttonsArray[3].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));


        root.getChildren().addAll(buttonsArray);
        return taleScene;
    }

}

