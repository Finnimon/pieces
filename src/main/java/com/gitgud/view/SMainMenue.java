package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SMainMenue {

    public static Scene createMainMenueScene(Stage stage)
    {
        Group root = new Group();
        Scene MainMenueScene = new Scene(root);
        stage.setScene(MainMenueScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind

        // Hier ein Beispiel, wie die Scenen Switcht:

        Button button =new Button("Continue");

        button.setOnAction(e -> stage.setScene(SDifficulty.createDifficultyScene(stage)));

        root.getChildren().add(button);
        return MainMenueScene;
    }
}
