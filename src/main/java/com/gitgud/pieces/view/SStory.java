package com.gitgud.pieces.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


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
        VBox root = GuiUtils.createVBox("src/main/resources/com/gitgud/backgroundImages/Papier.jpg");
        Scene StoryScene = new Scene(root, 800, 600);
        stage.setScene(StoryScene);
        
        // VBox für den Text
        VBox textBox = new VBox();
        textBox.setAlignment(Pos.BASELINE_CENTER);
        textBox.setPadding(new Insets(230, 10, 160, 10));
        textBox.setSpacing(10);
        
        String text = "In einer längst vergangenen Ära, als Magie und Mythen das Land Elaris prägten, herrschte " + "Frieden und Wohlstand. Doch eines unheilvollen Tages, erwachte der dunkle Zauberer Mykanos aus " + "seinem jahrhundertelangen Schlaf. " + "Mit mächtigen, bösartigen Kräften riss er das Licht aus der Welt und verbreitete Schrecken in jeder " + "Ecke des Königreichs. Du, ein Explorer mit verborgenen Kräften, wurdest durch eine uralte " + "Prophezeiung auserwählt, um das Gleichgewicht wiederherzustellen. Deine Reise beginnt in der letzten " + "Bastion des Lichts, der Stadt Elaris, wo die Hoffnung auf Erlösung glimmt und dein Schicksal sich entfaltet.";
        
        String[] storyLines = text.split(", "); //Der Text wird auf die Kommas aufgesplittet
        for (String line : storyLines)
        {
            Text textLine = new Text(line.trim() + ",");
            textLine.setFont(Font.font("Courier", 15)); // Retro Schriftart und Größe festlegen
            textLine.setFill(Color.BLACK); // Textfarbe festlegen
            textLine.setTextAlignment(TextAlignment.LEFT); // Textzentrierung
            textBox.getChildren().add(textLine);
        }
        
        Button button = new Button("Fortfahren");
        GuiUtils.setButtonTrait(button);
        
        button.setOnAction(e -> stage.setScene(STutorial.createTutorialScene(stage)));
        
        textBox.getChildren().add(button); //Button zur VBox hinzufügen
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(textBox, button);
        
        
        return StoryScene;
    }
}
