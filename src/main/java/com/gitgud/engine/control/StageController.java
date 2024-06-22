package com.gitgud.engine.control;

import javafx.stage.Stage;


public class StageController
{
    private static StageController instance = null;
    
    
    private final Stage stage;
    
    
    private StageController(Stage stage)
    {
        this.stage = stage;
    }
    
    
    public static StageController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException("Not yet initialized");
        }
        return instance;
    }
    
    
    public Stage getStage()
    {
        return stage;
    }
    
    
    public static void initialize(Stage stage)
    {
        if (instance != null)
        {
            throw new IllegalStateException("Already initialized");
        }
        instance = new StageController(stage);
    }
}
