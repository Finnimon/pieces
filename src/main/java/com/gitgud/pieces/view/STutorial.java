package com.gitgud.pieces.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class STutorial
{
    
    
    public static Scene createTutorialScene(Stage stage)
    {
        VBox root = GuiUtils.createVBox("/com/gitgud/menueimage/Wallpaper.png");
        root.setAlignment(Pos.CENTER); // Zentriert die Elemente vertikal und horizontal
        root.setPadding(new Insets(20)); // Setzt den Abstand vom Rand
        root.setSpacing(20); // Abstand zwischen den Elementen
        
        Scene tutorialScene = new Scene(root, 800, 600);
        stage.setScene(tutorialScene);
        
        Text tutorialText = new Text("Tutorial");
        tutorialText.setFont(Font.font("Verdana", FontWeight.MEDIUM, 30)); // Schriftart und Größe festlegen
        tutorialText.setFill(Color.HONEYDEW); //Farbe des texts festlegen
        
        
        String text = "Du landest in der Stadt in der es verschiedene Gebäude gibt. Du kannst in jedes Gebäude reingehen um unterschiedlihce Aktionen ausführen zu können. Beisielsweise kannst du Ressourcen kaufen oder Artefakte und deine Einheiten verbessern. Durch Besuchen des schwarzen Gebäudes, landest im Hauptquatier, wo du deine Mission auswählen kannst";
        String[] storyLines = text.split("\\. "); //Der Text wird auf die Kommas aufgesplittet
        
        VBox textBox = new VBox(); // VBox für den Text
        textBox.setSpacing(10); // Abstand zwischen den Textzeilen
        
        for (String line : storyLines)
        {
            Text textLine = new Text(line.trim() + ",");
            textLine.setFont(Font.font("Courier", 15)); // Retro Schriftart und Größe festlegen
            textLine.setFill(Color.HONEYDEW); // Textfarbe festlegen
            textLine.setTextAlignment(TextAlignment.LEFT); // Textzentrierung
            textBox.getChildren().add(textLine);
        }
        
        Button button = new Button("Fortfahren");
        button.setOnAction(e -> stage.setScene(SCity.createCityScene(stage)));
        root.getChildren().addAll(tutorialText, textBox, button);
        
        return tutorialScene;
    }
    
}
