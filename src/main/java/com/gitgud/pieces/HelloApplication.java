package com.gitgud.pieces;

import com.gitgud.view.SMainMenue;
import com.gitgud.view.SMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application
{
    
    public static void main(String[] args)
    {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setTitle("Pieces");
        stage.setScene(SMainMenue.createMainMenueScene(stage));
        stage.show();
    }
}