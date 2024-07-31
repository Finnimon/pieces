package com.gitgud.pieces.control;

import com.gitgud.pieces.model.game.ActiveGame;
import com.gitgud.pieces.model.game.GameState;
import com.gitgud.pieces.testing.TestAssets;
import org.jetbrains.annotations.NotNull;

import static com.gitgud.pieces.model.game.GameState.*;


/**
 * <p>Singleton pattern for {@link ActiveGame}
 * <p>Stores all global in-game data and determines GameState
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 * @see ActiveGame
 */
public class ActiveGameController
{
    /**
     * Singleton instance
     */
    private static ActiveGameController instance = null;
    
    
    /**
     * The current active game
     */
    private final ActiveGame activeGame;
    
    
    /**
     * Private Singleton constructor
     *
     * @param activeGame The current active game.
     */
    private ActiveGameController(@NotNull ActiveGame activeGame)
    {
        if (isInitialized())
        {
            throw new IllegalStateException("Already initialized");
        }
        
        this.activeGame = activeGame;
    }
    
    
    /**
     * Determines if the Singleton has been initialized.
     *
     * @return {@code true} if the Singleton has been initialized or else {@code false}.
     */
    private static boolean isInitialized()
    {
        return instance != null;
    }
    
    
    /**
     * <p>Initializes the Singleton with the test data.
     * <p>Remove from production.
     */
    public static void testInitialize()
    {
        initialize(TestAssets.getTestActiveGame());//todo!!!
    }
    
    
    /**
     * Initializes the Singleton.
     *
     * @param activeGame The current active game.
     * @throws IllegalStateException If the Precondition is not met.
     * @Precondition: The ActiveGameController must not be initialized.
     * @Postcondition: The ActiveGameController is initialized and no Exceptions will be thrown.
     */
    public static void initialize(@NotNull ActiveGame activeGame)
    {
        if (isInitialized())
        {
            throw new IllegalStateException("Already initialized");
        }
        instance = new ActiveGameController(activeGame);
    }
    
    
    /**
     * Resets the Singleton.
     */
    public static void reset()
    {
        instance = null;
    }
    
    
    /**
     * Getter for the GameState.
     *
     * @return The current GameState.
     * @Precondition: None.
     * @Postcondition: No side effects.
     * @see GameState
     */
    public static GameState getGameState()
    {
        if (!isInitialized())
        {
            return GameState.NOT_LOADED;
        }
        
        return getInitializedGameState();
    }
    
    
    /**
     * Determines the GameState based on the current ActiveGame.
     *
     * @return The current GameState.
     * @Precondition: The ActiveGameController must be initialized.
     * @Postcondition: The current GameState is determined and no exceptions will be thrown.
     */
    private static GameState getInitializedGameState()
    {
        ActiveGame activeGame = getInstance().get();
        boolean isMission = activeGame.getMission() != null;
        boolean isFight = activeGame.getFight() != null;
        
        if (isMission && isFight)
        {
            return MISSION_FIGHT;
        }
        if (isMission)
        {
            return MISSION;
        }
        if (isFight)
        {
            return ARENA_FIGHT;
        }
        return CITY;
    }
    
    
    /**
     * Getter for the current active game.
     *
     * @return The current active game.
     */
    public ActiveGame get()
    {
        return activeGame;
    }
    
    
    /**
     * Singleton getter.
     *
     * @return The Singleton instance.
     * @throws IllegalStateException If the Precondition is not met.
     * @Precondition: The ActiveGameController must be initialized.
     * @Postcondition: The returned ActiveGame is not null and no Exceptions will be thrown.
     */
    public static ActiveGameController getInstance()
    {
        if (!isInitialized())
        {
            throw new IllegalStateException("Not yet initialized");
        }
        return instance;
    }
}
