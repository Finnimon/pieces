package com.gitgud.pieces;

import com.gitgud.model.gameObjects.Sprite;
import com.gitgud.view.SMainMenue;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application
{
    public static void main(String[] args)
    {
        launch();
    }
    
    
    public static void delfiMain(Stage stage)
    {
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setScene(SMainMenue.createMainMenueScene(stage));
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        stage.setTitle("Pieces");
        stage.getIcons().add(new Image(Sprite.urlFromFilePath(
                "src\\main\\resources\\com\\gitgud\\sprites\\agents\\blackAndWhite\\black_king.png")));
        delfiMain(stage);
        stage.show();
    }
}