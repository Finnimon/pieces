package com.gitgud.pieces;

import com.gitgud.model.gameObjects.Sprite;
import com.gitgud.view.SMainMenue;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;


public class App extends Application
{
    
    
    public static final String ICON_PATH = "src\\main\\resources\\com\\gitgud\\sprites\\agents\\blackAndWhite\\black_king.png";
    
    
    public static final String APP_NAME = "Pieces";
    
    
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
        setTitleAndIcon(stage);
        delfiMain(stage);
        stage.show();
    }
    
    
    private static void setTitleAndIcon(Stage stage) throws MalformedURLException
    {
        stage.setTitle(APP_NAME);
        stage.getIcons().add(new Image(Sprite.urlFromFilePath(ICON_PATH)));
    }
}