package com.gitgud.pieces.model.game;

import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.model.player.Player;


/**
 * The data object used by the Singleton {@link com.gitgud.pieces.control.ActiveGameController}.
 * <p>Also used by {@link Game} as the saveFile.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.1
 * @see Game
 * @see com.gitgud.pieces.control.ActiveGameController
 */
public class ActiveGame
{
    /**
     * The Player of this active Game.
     */
    private final Player player;
    
    
    /**
     * The City of the active game.
     */
    private final City city;
    
    
    /**
     * The currently active Mission or null.
     */
    private Mission mission;
    
    
    /**
     * The currently active Fight or null.
     */
    private Fight fight;
    
    
    /**
     * Default Constructor that sets als values.
     *
     * @param player  The player for this active game.
     * @param city    The city for this active game.
     * @param mission The current mission for this active game.
     * @param fight   The current fight for this active game.
     */
    public ActiveGame(Player player, City city, Mission mission, Fight fight)
    {
        this.player = player;
        this.city = city;
        this.mission = mission;
        this.fight = fight;
    }
    
    
    /**
     * Gets the player.
     *
     * @return The player.
     */
    public Player getPlayer()
    {
        return player;
    }
    
    
    /**
     * Gets the city.
     *
     * @return The city.
     */
    public City getCity()
    {
        return city;
    }
    
    
    /**
     * Gets the currents mission.
     *
     * @return The current mission.
     */
    public Mission getMission()
    {
        return mission;
    }
    
    
    /**
     * Setter for the current mission.
     *
     * @param mission The new value for the current mission.
     */
    public void setMission(Mission mission)
    {
        this.mission = mission;
    }
    
    
    public Fight getFight()
    {
        return fight;
    }
    
    
    public void setFight(Fight fight)
    {
        this.fight = fight;
    }
}
