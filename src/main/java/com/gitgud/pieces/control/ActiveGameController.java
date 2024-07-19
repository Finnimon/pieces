package com.gitgud.pieces.control;

import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.testing.TestAssets;


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
            initialize();
        }
        return instance;
    }
    
    
    public static void reset()
    {
        instance = null;
    }
    
    
    public static void initialize()
    {
        initialize(TestAssets.getTestActiveGame());//todo!!!
    }
    
    
    public static void initialize(ActiveGame activeGame)
    {
        if (isInitialized())
        {
            return;
        }
        instance = new ActiveGameController(activeGame);
    }
    
    
    public static boolean isInitialized()
    {
        return instance != null;
    }
    
    
    
    
    public ActiveGame get()
    {
        return activeGame;
    }
}
