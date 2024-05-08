package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SStory {



    public static Scene createStoryScene(Stage stage)
    {
        Group root = new Group();
        Scene StoryScene = new Scene(root);
        stage.setScene(StoryScene);

        // Hier kannst du alles machen
// Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind
        return StoryScene;
    }
}
