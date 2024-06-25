package com.gitgud.pieces.view;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 23.06.2024
 */
public class SFactionCamps {

    private static String[] factions = {"F1", "F2", "F3"};

    public static Scene createFactionCampsScene(Stage stage) {
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);

        Label description = new Label("Hier können die Fraktionen freigeschaltet werden ");
        description.setFont(Font.font(null, FontWeight.BOLD, 20));

        HBox factionsBox = new HBox(30);
        factionsBox.setAlignment(Pos.CENTER);

        for (String faction : factions) {
            VBox factionBox = new VBox(10);
            factionBox.setAlignment(Pos.CENTER);
            Label label = new Label(faction);
            label.setFont(Font.font(null, FontWeight.MEDIUM, 20));
            Button recruit = new Button("Freischalten");

            recruit.setOnAction(e -> {
                System.out.println(faction + " freischalten");
            });

            factionBox.getChildren().addAll(label, recruit);
            factionsBox.getChildren().add(factionBox);
        }

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> SCity.createCityScene(stage));

        layout.getChildren().addAll(description, factionsBox, continueButton);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));

        stage.setScene(scene);
        return scene;
    }
}
