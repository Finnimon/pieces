package com.gitgud.pieces.control;

import com.gitgud.engine.control.StageController;
import com.gitgud.engine.control.Startable;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.utility.gsonSerialization.JsonParser;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.Executors;

import static com.gitgud.pieces.control.ActiveGameController.getInstance;
import static com.gitgud.pieces.control.ActiveGameController.isInitialized;


public final class GameFlow
{
    
    /**
     * Private Constructor for static class
     */
    private GameFlow()
    {
    }
    
    
    public static void initializeGame(Stage stage)
    {
        setStageToLoadScreen();
        JsonParser.getInstance();
        StageController.initialize(stage);
        
        showNextScene();
    }
    
    
    public static void showNextScene()
    {
        setStageToLoadScreen();
        Task<Startable> task = nextSceneControllerTask();
        Executors.newSingleThreadExecutor().execute(task);
    }
    
    private static Task<Startable> nextSceneControllerTask()
    {
        Task<Startable> task= new Task<Startable>()
        {
            @Override
            protected Startable call() throws Exception
            {
                return getNextSceneController();
            }
            
            
            @Override
            protected void succeeded()
            {
                getValue().start();
            }
        };
        
        return task;
    }
    
    
    public static void setStageToLoadScreen()
    {
        Stage stage = StageController.getInstance().getStage();
        Label label= new Label("Loading...");
        label.setFont(new Font(100));
        StackPane pane = new StackPane(label);
        StackPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        stage.getScene().setRoot(pane);
        stage.show();
    }
    
    

    
    
    public static Startable getNextSceneController()
    {
        if (!isInitialized())
        {
            //todo new MainMenuController().start();
            throw new RuntimeException();
        }
        
        ActiveGame activeGame = getInstance().get();
        GameState gameState = activeGame.getGameState();
        
        return switch (gameState)
        {
            case CITY -> new CityController(activeGame.getCity());
            case MISSION -> new MissionController(activeGame.getMission());
            case MISSION_FIGHT -> new FightController(activeGame.getFight());
            case ARENA_FIGHT -> throw new RuntimeException();
        };
    }
}
