package com.gitgud.pieces.view.sUnused;

import com.gitgud.pieces.model.gameobjects.FightAgentType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class STrainingCamp
{
    public static Scene createTrainingCampScene(Stage stage)
    {
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.CENTER);
        Label description = new Label("Hier können die Werte der Einheiten verbessert werden");
        HBox units = new HBox(20);
        units.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        for (FightAgentType unit : FightAgentType.values())
        {
            VBox vbox = new VBox(20);
            vbox.setAlignment(Pos.CENTER);
            Button upgrade = new Button("Upgrade");
            vbox.getChildren().addAll(new Label(unit.name()), upgrade);
            units.getChildren().add(vbox);
        }
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> SCity.createCityScene(stage));
        layout.getChildren().addAll(description, units, continueButton);
        stage.setScene(scene);
        return scene;
    }
}
