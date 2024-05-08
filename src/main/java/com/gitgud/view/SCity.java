package com.gitgud.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SCity {


    public static Scene createCityScene(Stage stage)
    {
        Group root = new Group();
        Scene CityScene = new Scene(root);
        stage.setScene(CityScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind
        return CityScene;
    }
}
