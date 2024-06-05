package com.gitgud.model.activeGame;

import com.gitgud.model.city.City;
import com.gitgud.model.fight.Fight;
import com.gitgud.model.mission.Mission;
import com.gitgud.model.player.Player;
import javafx.stage.Stage;

/**
 * The data object used by the Singleton {@link com.gitgud.control.ActiveGameController}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class ActiveGame
{
    private final Stage stage;
    
    
    private final Player player;
    
    
    private final City city;
    
    
    private Mission mission;
    
    
    private Fight fight;
    
    
    private ActiveGame(Stage stage, Player player, City city, Mission mission, Fight fight)
    {
        this.stage = stage;
        this.player = player;
        this.city = city;
        this.mission = mission;
        this.fight = fight;
    }
    
    
    public static ActiveGame create(Stage stage, Player player, City city, Mission mission, Fight fight)
    {
        return new ActiveGame(stage, player, city, mission, fight);
    }
    
    
    public static ActiveGame create(Stage stage, Player player, City city)
    {
        return new ActiveGame(stage, player, city, null, null);
    }
    
    
    public Stage getStage()
    {
        return stage;
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
        boolean mission=this.getMission()!=null;
        boolean fight=this.getFight()!=null;
        
        
        return GameState.getGameState(mission, fight);
    }
    
    
}
