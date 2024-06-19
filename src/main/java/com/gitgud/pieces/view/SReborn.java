package com.gitgud.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 18.06.2024
 */
public class SReborn {

    private static String[] content = {"a", "b", "c", "d", "e"};

    public static Scene createRebornScene(Stage stage){
        HBox hbox = new HBox();
        Scene rebornScene = new Scene(hbox);

        VBox playerBox = new VBox(50);
        playerBox.setAlignment(Pos.TOP_CENTER);
        playerBox.getChildren().add(new Label("Player"));
        playerBox.setMinWidth(stage.getWidth() / (content.length  + 1));
        hbox.getChildren().add(playerBox);

        for (int i = 0; i < content.length; i++) {
            VBox vbox = new VBox(50);
            Button button = new Button("Choose");
            button.setOnAction(e -> {
                SUnits.createUnitsScene(stage);
            });
            vbox.setAlignment(Pos.TOP_CENTER);
            vbox.getChildren().addAll(new Label(content[i]), button);
            vbox.setMinWidth(stage.getWidth() / (content.length  + 1));
            hbox.getChildren().add(vbox);
        }

        stage.setScene(rebornScene);
        return rebornScene;
    }
}
