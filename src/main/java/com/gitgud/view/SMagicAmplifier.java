package com.gitgud.view;
import com.gitgud.model.fight.SpellType;
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
public class SMagicAmplifier {

    private static int level = 1;

    public static Scene createMAScene(Stage stage) {
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        HBox spells = new HBox(60);
        spells.setAlignment(Pos.CENTER);

        for (int i = 0; i < SpellType.values().length; i++) {
            VBox spell = new VBox(60);
            spell.setAlignment(Pos.CENTER);
            Label name = new Label(SpellType.values()[i].name()+ " Level: " + level);
            name.setFont(Font.font(null, FontWeight.MEDIUM, 20));
            Button upgrade = new Button("Verstärken");
            spell.getChildren().addAll(name, upgrade);
            spells.getChildren().add(spell);
            int finalI = i;
            upgrade.setOnAction(e -> {
                //TODO: Methode zum upgraden aufrufen
                level++;
                name.setText(SpellType.values()[finalI].name()+ " Level: " + level);
            });
        }

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> SCity.createCityScene(stage));
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        layout.getChildren().addAll(spells, continueButton);


        stage.setScene(scene);
        return scene;
    }
}
