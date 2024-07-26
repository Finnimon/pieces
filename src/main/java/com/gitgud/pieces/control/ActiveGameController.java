package com.gitgud.pieces.control;

import com.gitgud.pieces.model.activeGame.ActiveGame;
import com.gitgud.pieces.model.activeGame.GameState;
import com.gitgud.pieces.testing.TestAssets;

import static com.gitgud.pieces.model.activeGame.GameState.*;


/**
 * Stores all global data and determines GameState
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
        if (!isInitialized())
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
    
    
    private static boolean isInitialized()
    {
        return instance != null;
    }
    
    
    public static GameState getGameState()
    {
        if (!isInitialized()) return GameState.NOT_LOADED;
        
        return getInitializedGameState();
    }
    
    
    private static GameState getInitializedGameState()
    {
        ActiveGame activeGame = instance.get();
        boolean isMission = activeGame.getMission() != null;
        boolean isFight = activeGame.getFight() != null;
        
        if (isMission && isFight) return MISSION_FIGHT;
        if (isMission) return MISSION;
        if (isFight) return ARENA_FIGHT;
        return CITY;
    }
    
    
    public ActiveGame get()
    {
        return activeGame;
    }
}
