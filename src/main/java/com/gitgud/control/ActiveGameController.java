package com.gitgud.control;

import com.gitgud.model.activeGame.ActiveGame;


public class ActiveGameController
{
    private static ActiveGameController instance = null;
    
    
    private final ActiveGame activeGame;
    
    
    private ActiveGameController(ActiveGame activeGame)
    {
        this.activeGame = activeGame;
    }
    
    
    public static ActiveGameController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException();
        }
        
        
        return instance;
    }
    
    
    public ActiveGame get()
    {
        return activeGame;
    }
    
    
    public static void reset()
    {
        instance = null;
    }
    
    
    public static void initialize()
    {
    
    }
    
    
    public void initialize(ActiveGame activeGame)
    {
        if (instance != null)
        {
            return;
        }
        instance = new ActiveGameController(activeGame);
    }
}
