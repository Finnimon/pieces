package com.gitgud.pieces.view;

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
 * @since 17.05.2024
 */

public class SDifficulty {

    public static Scene createDifficultyScene(Stage stage) {
        String difficultySceneBackgroundPath = "C:/Users/delfi/Desktop/2 Semester 2024/GUI_prog/GUI_pics.jpg";
        VBox rootD = GuiUtils.createVBox(difficultySceneBackgroundPath);
        Scene DifficultyScene = new Scene(rootD);
        stage.setScene(DifficultyScene);

        Text difficultyMenueText = new Text("Schwierigkeitsgrad");
        difficultyMenueText.setFont(Font.font("Lato", FontWeight.MEDIUM, 24)); // Schriftart und Größe festlegen
        rootD.getChildren().add(difficultyMenueText);

        Button button = new Button();

        //erstellen der Szene mit den buttons etc
        Button[] buttonsArrayD = new Button[3];
        buttonsArrayD[0] = new Button("Einfach");
        buttonsArrayD[1] = new Button("Normal");
        buttonsArrayD[2] = new Button("Schwer");

        //Größe und Schriftart für jeweils alle Buttons festlegen
        for (Button buttonD : buttonsArrayD) {
            GuiUtils.setButtonTrait(buttonD);
        }

           buttonsArrayD[0].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage))); //TODO: inwifern werden die Schwierigkeitsgrade dargestellt? Weniger Einheiten die man in den Kampf mitnehmen darf?
           buttonsArrayD[1].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
           buttonsArrayD[2].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));


        rootD.getChildren().addAll(buttonsArrayD);
        return DifficultyScene;
    }

}
