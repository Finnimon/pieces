package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SFight {


    public static Scene createFightScene(Stage stage)
    {
        Group root = new Group();
        Scene FightScene = new Scene(root);
        stage.setScene(FightScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind
        return FightScene;
    }
}
