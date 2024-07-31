package com.gitgud.pieces.model.mission;

import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.city.City;
import com.gitgud.pieces.model.city.buildings.headQuarter.HeadQuarters;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;


public class Mission implements ActionAwaiterModel<GameObject>
{
    private final int index;
    
    
    private final GridMap<GameObject> gridMap;
    
    
    private final PlayerAgent playerAgent;
    
    
    private final FightAgent[] activeFightAgents;
    
    
    private final FightAgent[] discardedFightAgents;
    
    
    private final SimpleIntegerProperty turn;
    
    
    private Tile playerAgentPosition;
    
    
    private boolean finished = false;
    
    
    public Mission(GridMap<GameObject> gridMap, Tile startingPosition, FightAgent[] activeFightAgents, int index)
    {
        this(index,
             gridMap,
             new PlayerAgent(),
             startingPosition,
             activeFightAgents,
             new FightAgent[activeFightAgents.length]);
    }
    
    
    public Mission(int index, GridMap<GameObject> gridMap, PlayerAgent playerAgent, Tile playerAgentPosition,
                   FightAgent[] activeFightAgents, FightAgent[] discardedFightAgents)
    {
        this.index = index;
        this.gridMap = gridMap;
        this.playerAgent = playerAgent;
        this.playerAgentPosition = playerAgentPosition;
        this.activeFightAgents = activeFightAgents;
        this.discardedFightAgents = discardedFightAgents;
        this.turn = new SimpleIntegerProperty(0);
    }
    
    
    public GridMap<GameObject> getGridMap()
    {
        return gridMap;
    }
    
    
    public PlayerAgent getPlayerAgent()
    {
        return playerAgent;
    }
    
    
    public Tile getPlayerAgentPosition()
    {
        return playerAgentPosition;
    }
    
    
    public void setPlayerAgentPosition(Tile playerAgentPosition)
    {
        this.playerAgentPosition = playerAgentPosition;
    }
    
    
    public FightAgent[] getActiveFightAgents()
    {
        return activeFightAgents;
    }
    
    
    public FightAgent[] getDiscardedFightAgents()
    {
        return discardedFightAgents;
    }
    
    
    public boolean isFinished()
    {
        return this.finished;
    }
    
    
    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }
    
    
    public void end()
    {
        returnFightAgentsToArmy();
        City city = ActiveGameController.getInstance().get().getCity();
        HeadQuarters headQuarters = city.getHeadQuarters();
        while (index > headQuarters.getLevel())
            headQuarters.levelUp();
    }
    
    
    public void returnFightAgentsToArmy()
    {
        Collection<FightAgent> fightAgents = ActiveGameController.getInstance().get().getPlayer().army();
        fightAgents.addAll(getFightAgents());
    }
    
    
    public Collection<FightAgent> getFightAgents()
    {
        HashSet<FightAgent> fightAgents = new HashSet<>();
        fightAgents.addAll(Arrays.asList(activeFightAgents));
        fightAgents.addAll(Arrays.asList(discardedFightAgents));
        fightAgents.removeIf(Objects::isNull);
        return fightAgents;
    }
    
    
    @Override
    public SimpleIntegerProperty turnProperty()
    {
        return turn;
    }
}
