package com.gitgud.pieces.view;

import com.gitgud.engine.model.gameobjects.Sprite;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 22.06.2024
 */

public class STavern {
    private static String[] availableUnits = {"Bishop", "King", "Knight", "Pawn", "Queen", "Rook", };


    public static Scene createTavernScene(Stage stage) {

        Label selected = new Label();
        Label description = new Label("Hier können Söldner angeworben werden");
        description.setFont(Font.font(null, FontWeight.BOLD, 20));

        ArrayList<String> selectedUnits = new ArrayList<>();
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        HBox units = new HBox(60);
        units.setAlignment(Pos.CENTER);

        String[] imagePaths = {
                "src/main/resources/com/gitgud/sprites/agents/blackAndWhite/black_bishop.png",
                "src/main/resources/com/gitgud/sprites/agents/blackAndWhite/black_king.png",
                "src/main/resources/com/gitgud/sprites/agents/blackAndWhite/black_knight.png",
                "src/main/resources/com/gitgud/sprites/agents/blackAndWhite/black_pawn.png",
                "src/main/resources/com/gitgud/sprites/agents/blackAndWhite/black_queen.png",
                "src/main/resources/com/gitgud/sprites/agents/blackAndWhite/black_rook.png"
        };

        for (int i = 0; i < availableUnits.length; i++) {
            VBox unit = new VBox(60);
            unit.setAlignment(Pos.CENTER);
            Label name = new Label(availableUnits[i]);
            name.setFont(Font.font(null, FontWeight.MEDIUM, 20));

            Image unitImage = null;
            try {
                unitImage = new Image(Sprite.urlFromFilePath(imagePaths[i]));
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }

            ImageView unitImages = new ImageView(unitImage);
            unitImages.setFitWidth(50); // Setze die Breite des Bildes
            unitImages.setFitHeight(50); // Setze die Höhe des Bildes

            Button select = new Button("Select Unit");
            unit.getChildren().addAll(name, unitImages, select);
            units.getChildren().add(unit);
            int finalI = i;
            select.setOnAction(e -> {
                selectedUnits.add(availableUnits[finalI]);
                selected.setText(selectedUnits.toString().replace("[", "").replace("]", ""));
            });
        }

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> SCity.createCityScene(stage));
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        layout.getChildren().addAll(description, selected, units, continueButton);


        stage.setScene(scene);
        return scene;
    }

}
