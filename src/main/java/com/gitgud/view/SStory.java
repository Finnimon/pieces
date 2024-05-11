package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SStory {



    public static Scene createStoryScene(Stage stage)
    {
        Group root = new Group();
        Scene StoryScene = new Scene(root);
        stage.setScene(StoryScene);

        // Hier kannst du alles machen

        Button button = new Button("Continuo");
        button.setOnAction(e -> stage.setScene(STutorial.createTutorialScene(stage)));
        root.getChildren().addAll(button);
        return StoryScene;
    }
}
