package com.gitgud.pieces.control;

import com.gitgud.engine.control.StageController;
import com.gitgud.engine.control.Startable;
import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.utility.gsonSerialization.JsonParser;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.util.concurrent.Executors;

import static com.gitgud.pieces.control.ActiveGameController.getInstance;
import static com.gitgud.pieces.control.ActiveGameController.isInitialized;


public final class GameFlow
{
    private static final Task<Startable> NEXT_SCENE_CONTROLLER_TASK = new Task<Startable>()
    {
        @Override
        protected Startable call() throws Exception
        {
            return getNextSceneController();
        }
    };
    
    
    /**
     * Private Constructor for static class
     */
    private GameFlow()
    {
    }
    
    
    public static void setStageToLoadScreen()
    {
        //todo
    }
    
    
    public static void initializeGame(Stage stage)
    {
        setStageToLoadScreen();
        JsonParser.getInstance();
        StageController.initialize(stage);
        
        startNextSceneController();
    }
    
    
    public static void startNextSceneController()
    {
        setStageToLoadScreen();
        Task<Startable> task = NEXT_SCENE_CONTROLLER_TASK;
        task.setOnSucceeded(x -> task.getValue().start());
        Executors.newSingleThreadExecutor().execute(task);
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
