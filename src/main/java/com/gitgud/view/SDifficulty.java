package com.gitgud.view;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.net.MalformedURLException;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 17.05.2024
 */

public class SDifficulty {

    public static Scene createDifficultyScene(Stage stage) {
        String difficultySceneBackgroundPath = "src\\main\\resources\\com\\gitgud\\backgroundImages\\Wallpaper.png";
        VBox rootD = GuiUtils.createVBox(difficultySceneBackgroundPath);
        Scene DifficultyScene = new Scene(rootD);
        stage.setScene(DifficultyScene);


        Font customFont = null;
        Label difficultyMenueText = null;
        try {

            customFont = GuiUtils.loadFont(25);

            difficultyMenueText = new Label("Schwierigkeitsgrad");
            difficultyMenueText.setFont(customFont); // Schriftart und Größe festlegen
            difficultyMenueText.setTextFill(Color.LIGHTSLATEGRAY);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());

        }


        //erstellen der Szene mit den buttons etc
        Button[] buttonsArrayD = new Button[3];
        buttonsArrayD[0] = new Button("Einfach");
        buttonsArrayD[1] = new Button("Normal");
        buttonsArrayD[2] = new Button("Schwer");


        // Größe und Schriftart für alle Buttons festlegen
        if (customFont != null) {
            GuiUtils.setButtonTraits(buttonsArrayD, customFont);
        }


        buttonsArrayD[0].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage))); //TODO: inwifern werden die Schwierigkeitsgrade dargestellt? Weniger Einheiten die man in den Kampf mitnehmen darf?
        buttonsArrayD[1].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        buttonsArrayD[2].setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));

        rootD.getChildren().add(difficultyMenueText);
        rootD.getChildren().addAll(buttonsArrayD);
        return DifficultyScene;
    }

}
