package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SDifficulty {

    public static Scene createDifficultyScene(Stage stage)
    {
        Group root = new Group();
        Scene DifficultyScene = new Scene(root);
        stage.setScene(DifficultyScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind
        Text text = new Text("Es hat geklapt");
        root.getChildren().add(text);

        return DifficultyScene;
    }
}
