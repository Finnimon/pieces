package com.gitgud.pieces;

import com.gitgud.view.SMainMenue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application
{
    public static void juliusStart(Stage stage) throws IOException
    {
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setTitle("Pieces");
        
        stage.setScene(SMainMenue.createMainMenueScene(stage));
        stage.show();
    }
    public static void defaultStart(Stage stage) throws IOException
    {
        
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void main(String[] args)
    {
        launch();
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        juliusStart(stage);
    }
}