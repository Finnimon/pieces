package com.gitgud.pieces.model.game;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.Game;


/**
 * Used for indicating the current state of the Game.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 * @see ActiveGame
 * @see ActiveGameController#getGameState()
 * @see Game.Flow#showNextScene()
 */
public enum GameState
{
    /**
     * The {@link com.gitgud.pieces.control.ActiveGameController} is not Initialized.
     * <p>Therefore the current Scene must be the {@link com.gitgud.pieces.view.MainMenu} or one of its SubMenus
     *
     * @see com.gitgud.pieces.control.MainMenuController
     */
    NOT_LOADED,
    /**
     * The ActiveGame contains neither a {@link com.gitgud.pieces.model.fight.Fight} nor a
     * {@link com.gitgud.pieces.model.mission.Mission}.
     * <p> Therefore the current Scene is in the City
     *
     * @see com.gitgud.pieces.control.CityController
     */
    CITY,
    
    /**
     * The ActiveGame contains a mission but not a fight.
     * <p>Therefore the current scene is the mission
     *
     * @see com.gitgud.pieces.control.MissionController
     */
    MISSION,
    /**
     * The ActiveGame contains a mission and a fight.
     * <p>Therefore the Fight must be Fight within a Mission.
     *
     * @see com.gitgud.pieces.control.FightController
     */
    MISSION_FIGHT,
    /**
     * The Active Game contains a standalone {@link com.gitgud.pieces.model.fight.Fight}
     * <p>Therefore said fight is an online Arena fight.
     *
     * @see com.gitgud.pieces.control.Net.ArenaController
     */
    ARENA_FIGHT
}
