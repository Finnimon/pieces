package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class STutorial {


    public static Scene createTutorialScene(Stage stage)
    {
        Group root = new Group();
        Scene TutorialScene = new Scene(root);
        stage.setScene(TutorialScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind

        Button button = new Button("Continuo");
        button.setOnAction(e -> stage.setScene(SCity.createCityScene(stage)));
        root.getChildren().addAll(button);

        return TutorialScene;
    }
}
