package com.gitgud.pieces.model.activeGame;

import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.model.player.Player;


/**
 * The data object used by the Singleton {@link com.gitgud.pieces.control.ActiveGameController}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class ActiveGame
{
    
    private final Player player;
    
    
    private final City city;
    
    
    private Mission mission;
    
    
    private Fight fight;
    
    
    private ActiveGame(Player player, City city, Mission mission, Fight fight)
    {
        this.player = player;
        this.city = city;
        this.mission = mission;
        this.fight = fight;
    }
    
    
    public static ActiveGame create(Player player, City city, Mission mission, Fight fight)
    {
        return new ActiveGame(player, city, mission, fight);
    }
    
    
    public static ActiveGame create(Player player, City city)
    {
        return new ActiveGame(player, city, null, null);
    }
    
    
    public Player getPlayer()
    {
        return player;
    }
    
    
    public City getCity()
    {
        return city;
    }
    
    
    public Mission getMission()
    {
        return mission;
    }
    
    
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
    
    
    public GameState getGameState()
    {
        boolean mission = this.getMission() != null;
        boolean fight = this.getFight() != null;
        
        
        return GameState.getGameState(mission, fight);
    }
    
    
}
