package com.gitgud.pieces.view.sUnused;

import com.gitgud.pieces.view.GuiUtils;
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


/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 15.05.2024
 */
public class SStory
{
    
    
    public static Scene createStoryScene(Stage stage)
    {
        VBox root = GuiUtils.createVBox("src\\main\\resources\\com\\gitgud\\backgroundImages\\Wallpaper.png");
        root.setAlignment(Pos.CENTER); // Zentriert die Elemente vertikal und horizontal
        root.setPadding(new Insets(20)); // Setzt den Abstand vom Rand
        root.setSpacing(20); // Abstand zwischen den Elementen
        
        Scene storyScene = new Scene(root, 800, 600);
        stage.setScene(storyScene);
        
        Label StoryText = new Label("Geschichte");
        StoryText.setTextFill(Color.LIGHTSLATEGRAY);
        Font customFont = null;
        
        try
        {
            customFont = GuiUtils.loadFont(25);
        }
        catch (MalformedURLException a)
        {
            System.out.println("Schriftart konnte nicht geladen werden");
        }
        if (customFont == null)
        {
            System.out.println("Schriftart konnte nicht geladen werden!");
        }
        else
        {
            StoryText.setFont(customFont);
        }
        
        String text = "In einer längst vergangenen Ära, als Magie und Mythen das Land Elaris prägten, herrschte " +
                      "Frieden und Wohlstand. Doch eines unheilvollen Tages, erwachte der dunkle Zauberer Mykanos aus" +
                      " seinem jahrhundertelangen Schlaf. Mit mächtigen, bösartigen Kräften riss er das Licht aus der" +
                      " Welt und verbreitete Schrecken in jeder Ecke des Königreichs. Du, ein Explorer mit " +
                      "verborgenen Kräften, wurdest durch eine uralte Prophezeiung auserwählt, um das Gleichgewicht " +
                      "wiederherzustellen. Deine Reise beginnt in der letzten Bastion des Lichts, der Stadt Elaris, " +
                      "wo die Hoffnung auf Erlösung glimmt und dein Schicksal sich entfaltet.";
        String[] storyLines = text.split("\\. "); //Der Text wird auf die Kommas aufgesplittet
        
        VBox textBox = new VBox(); // VBox für den Text
        textBox.setAlignment(Pos.BASELINE_CENTER);
        textBox.setSpacing(10); // Abstand zwischen den Textzeilen
        
        for (String line : storyLines)
        {
            Text textLine = new Text(line.trim() + ",");
            textLine.setFont(Font.font("Courier", 15)); // Retro Schriftart und Größe festlegen
            textLine.setFill(Color.HONEYDEW); // Textfarbe festlegen
            textBox.getChildren().add(textLine);
        }
        
        Button button = new Button("Fortfahren");
        if (customFont != null)
        {
            GuiUtils.setButtonTrait(button, customFont);
        }
        button.setOnAction(e -> stage.setScene(STutorial.createTutorialScene(stage)));
        root.getChildren().addAll(StoryText, textBox, button);
        
        return storyScene;
        
        
    }
    
}


