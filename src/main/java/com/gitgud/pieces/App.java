package com.gitgud.pieces;


import com.gitgud.engine.control.StageController;
import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.pieces.view.SMainMenue;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.gitgud.pieces.testing.TestStuff.lindigTest;


public class App extends Application
{
    
    
    public static final String ICON_PATH = "src\\main\\resources\\com\\gitgud\\sprites\\agents\\blackAndWhite\\black_king.png";
    
    
    public static final String APP_TITLE = "Pieces";
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        launch();
    }
    
    
    public static void delfiMain(Stage stage)
    {
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setScene(SMainMenue.createMainMenueScene(stage));
    }
    
    
    private static void setTitleAndIcon(Stage stage)
    {
        stage.setTitle(APP_TITLE);
        try
        {
            stage.getIcons().add(new Image(Sprite.urlFromFilePath(ICON_PATH)));
        }
        catch (MalformedURLException ignore)
        {
            //do nothing only sets the icon
        }
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {//call your main here
        //initialize(stage);
        //lindigTest(stage);
                delfiMain(stage);
        stage.show();
    }
    
    
    private void initialize(Stage stage)
    {
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(new Group()));
        stage.setFullScreen(true);
        StageController.initialize(stage);
        setTitleAndIcon(stage);
    }
}