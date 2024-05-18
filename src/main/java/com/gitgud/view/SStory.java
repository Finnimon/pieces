package com.gitgud.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 15.05.2024
 */
public class SStory {



    public static Scene createStoryScene(Stage stage)
    {
        VBox root = GuiUtils.createVBox("C:/Users/delfi/Desktop/2 Semester 2024/GUI_prog/Papier.jpg");
        Scene StoryScene = new Scene(root, 800, 600);
        stage.setScene(StoryScene);

        // VBox für den Text
        VBox textBox = new VBox();
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(50));
        textBox.setSpacing(20);

        Text text = new Text("blablabla");
        text.setFont(Font.font("Courier", 24)); // Retro Schriftart und Größe festlegen
        text.setFill(Color.BLACK); // Textfarbe festlegen

        Button button = new Button("Continue");
        GuiUtils.setButtonTrait(button);

        button.setOnAction(e -> stage.setScene(STutorial.createTutorialScene(stage)));

        textBox.getChildren().addAll(text, button);
        root.getChildren().addAll(textBox);
        return StoryScene;


    }
}
