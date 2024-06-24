package com.gitgud.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.MalformedURLException;

public class STutorial {


    public static Scene createTutorialScene(Stage stage) {
        VBox root = GuiUtils.createVBox("src\\main\\resources\\com\\gitgud\\backgroundImages\\Wallpaper.png");
        root.setAlignment(Pos.CENTER); // Zentriert die Elemente vertikal und horizontal
        root.setPadding(new Insets(20)); // Setzt den Abstand vom Rand
        root.setSpacing(20); // Abstand zwischen den Elementen

        Scene tutorialScene = new Scene(root, 800, 600);
        stage.setScene(tutorialScene);

        Label tutorialText = new Label("Tutorial");
        tutorialText.setTextFill(Color.LIGHTSLATEGRAY);
        Font customFont = null;

        try {
            customFont = GuiUtils.loadFont(25);
        } catch (MalformedURLException a) {
            System.out.println("Schriftart konnte nicht geladen werden");
            }
        if (customFont == null) {
            System.out.println("Schriftart konnte nicht geladen werden!");
        } else {
            tutorialText.setFont(customFont);
        }

        String text = "Du landest in der Stadt in der es verschiedene Gebäude gibt. Du kannst in jedes Gebäude reingehen um unterschiedlihce Aktionen ausführen zu können. Beisielsweise kannst du Ressourcen kaufen oder Artefakte und deine Einheiten verbessern. Durch Besuchen des schwarzen Gebäudes, landest im Hauptquatier, wo du deine Mission auswählen kannst";
        String[] tutorialLines = text.split("\\. "); //Der Text wird auf die Kommas aufgesplittet

        VBox textBox = new VBox(); // VBox für den Text
        textBox.setAlignment(Pos.BASELINE_CENTER);
        textBox.setSpacing(10); // Abstand zwischen den Textzeilen

        for (String line : tutorialLines) {
            Text textLine = new Text(line.trim() + ",");
            textLine.setFont(Font.font("Courier", 15)); // Retro Schriftart und Größe festlegen
            textLine.setFill(Color.HONEYDEW); // Textfarbe festlegen
            textBox.getChildren().add(textLine);
        }

        Button button = new Button("Fortfahren");
        if (customFont != null) {
            GuiUtils.setButtonTrait(button, customFont);
        }
        button.setOnAction(e -> stage.setScene(SCity.createCityScene(stage)));
        root.getChildren().addAll(tutorialText, textBox, button);

        return tutorialScene;


        }

    }