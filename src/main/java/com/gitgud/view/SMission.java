package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SMission {


    public static Scene createMissionScene(Stage stage)
    {
        Group root = new Group();
        Scene MissionScene = new Scene(root);
        stage.setScene(MissionScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind
        return MissionScene;
    }
}
