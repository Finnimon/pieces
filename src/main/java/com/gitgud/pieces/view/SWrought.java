package com.gitgud.pieces.view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.net.MalformedURLException;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 12.06.2024
 */

public class SWrought {

    private static Font customFont;

    public static Scene createWroughtScene(Stage stage) {


        VBox root = new VBox();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        Label wroughtMenueText = new Label("Schmiede");
        wroughtMenueText.setFont(Font.font("Verdana", FontWeight.MEDIUM, 30)); // Schriftart und Größe festlegen
        wroughtMenueText.setTextFill(Color.BISQUE); //Farbe des texts festlegen
        root.getChildren().add(wroughtMenueText);



        //Erstellen der Szene mit den buttons etc
        Button[] buttonsArray = new Button[2];
        buttonsArray[0] = new Button("Artefakte verbessern");
        buttonsArray[1] = new Button("Artefakte schmieden");


        try {
            customFont = GuiUtils.loadFont(25); // Schriftgröße 20 verwendet
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        if (customFont == null) {
            System.out.println("Schriftart konnte nicht geladen werden!");
        } else {
            wroughtMenueText.setFont(customFont);
        }


        if (customFont != null) {
            GuiUtils.setButtonTraits(buttonsArray, customFont);
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


