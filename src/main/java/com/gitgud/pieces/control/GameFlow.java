package com.gitgud.pieces.control;

import com.gitgud.engine.control.Startable;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.utility.gsonSerialization.JsonParser;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.Executors;


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
        return new Task<>()
        {
            @Override
            protected Startable call()
            {
                return getNextSceneController();
            }
            
            
            @Override
            protected void succeeded()
            {
                getValue().start();
            }
        };
    }
    
    
    public static void setStageToLoadScreen()
    {
        Label label= new Label("Loading...");
        label.setFont(new Font(100));
        StackPane pane = new StackPane(label);
        StackPane.setAlignment(label, javafx.geometry.Pos.CENTER);
        StageController stageController=StageController.getInstance();
        stageController.setRoot(pane);
        stageController.show();
    }
    
    

    
    
    public static Startable getNextSceneController()
    {
        GameState gameState = ActiveGameController.getGameState();
        return switch (gameState)
        {
            case NOT_LOADED -> throw new RuntimeException("Not implemented");
            case CITY -> new CityController(getActiveGame().getCity());
            case MISSION -> new MissionController(getActiveGame().getMission());
            case MISSION_FIGHT -> new FightController(getActiveGame().getFight());
            case ARENA_FIGHT -> throw new RuntimeException();
        };
    }
    
    private static ActiveGame getActiveGame()
    {
        return ActiveGameController.getInstance().get();
    }
}
