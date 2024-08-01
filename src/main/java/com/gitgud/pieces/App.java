package com.gitgud.pieces;


import com.gitgud.pieces.control.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * The main class of the application.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.07.2024
 * @Version: 3.0
 */
public class App extends Application
{
    public static void main(String[] arguments) throws IOException, ClassNotFoundException
    {
        launch();
    }
    
    
    /**
     * Starts the application and initializes the game.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) throws IOException
    {
        Game.Flow.initializeGame(stage);
    }
    
    
    /**
     * <p> The singletons are reset because garbage collection is not
     * guaranteed.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void stop() throws Exception
    {
        StageController.reset();
        ActiveGameController.reset();
        GameSettings.reset();
        Translator.reset();
        super.stop();
    }
}