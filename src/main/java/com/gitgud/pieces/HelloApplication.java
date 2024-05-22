package com.gitgud.pieces;

import com.gitgud.model.player.ResourceType;
import com.gitgud.view.SMainMenue;
import javafx.application.Application;
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
        System.out.println(ResourceType.GOLD.getSpriteUrl());
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setTitle("Pieces");
        stage.setScene(SMainMenue.createMainMenueScene(stage));
        stage.show();
    }
}