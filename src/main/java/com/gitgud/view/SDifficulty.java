package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SDifficulty {

    public static Scene createDifficultyScene(Stage stage)
    {
        Group root = new Group();
        Scene DifficultyScene = new Scene(root);
        stage.setScene(DifficultyScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Attribute hinzufügst und alle Methoden Static sind sonst wird alles rot und funktioniert nicht mehr

        Text text = new Text();
        text.setY(0);
        text.setX(0);
        Button button = new Button("Continue");

        button.setOnAction(e -> stage.setScene(SStory.createStoryScene(stage)));
        root.getChildren().addAll(button);

        return DifficultyScene;
    }
}
