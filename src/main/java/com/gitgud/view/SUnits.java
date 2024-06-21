package com.gitgud.view;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 18.06.2024
 */
public class SUnits {

    private static String[] availableUnits = {"Bishop", "King", "Knight", "Pawn", "Queen", "Rook"};
    private static int numberOfUnitsSelected = 0;

    public static Scene createUnitsScene(Stage stage) {
        ArrayList<Integer> selectedUnits = new ArrayList<>();
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        HBox units = new HBox(60);
        units.setAlignment(Pos.CENTER);
        for (int i = 0; i < availableUnits.length; i++) {
            VBox unit = new VBox(60);
            unit.setAlignment(Pos.CENTER);
            Label name = new Label(availableUnits[i]);
            Button select = new Button("Select Unit");
            unit.getChildren().addAll(name, select);
            units.getChildren().add(unit);
            int finalI = i;
            select.setOnAction(e -> {
                if (!selectedUnits.contains(finalI) && numberOfUnitsSelected < 4) {
                    select.setText("Deselect unit");
                    incrementUnitsSelected();
                    selectedUnits.add(finalI);
                    System.out.println(selectedUnits);
                } else if (selectedUnits.contains(finalI)) {
                    select.setText("Select Unit");
                    decrementUnitsSelected();
                    Integer unitToRemove = finalI;
                    selectedUnits.remove(unitToRemove);
                    System.out.println(selectedUnits);
                }
            });
        }

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> SMission.createMissionScene(stage));
        layout.getChildren().addAll(units, continueButton);


        stage.setScene(scene);
        return scene;
    }

    private static void incrementUnitsSelected() {
        numberOfUnitsSelected++;
    }

    private static void decrementUnitsSelected() {
        numberOfUnitsSelected--;
    }
}
