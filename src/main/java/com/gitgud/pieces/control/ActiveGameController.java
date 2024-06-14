package com.gitgud.pieces.control;

import com.gitgud.pieces.model.activeGame.ActiveGame;


/**
 * Stores all global data and handles GameState/Flow
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
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
        //todo default method
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
