package com.gitgud.pieces;


import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.control.game.Game;
import com.gitgud.pieces.view.sUnused.SMainMenue;
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
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        Game.Flow.initializeGame(stage);
    }
    

    
    
    /**
     * <p> {@link StageController} and {@link ActiveGameController} are reset because garbage collection is not
     * guaranteed.
     * {@inheritDoc}
     */
    @Override
    public void stop() throws Exception
    {
        StageController.reset();
        ActiveGameController.reset();
        super.stop();
    }
}