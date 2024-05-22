package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;


public class FightAgentController extends GridMovableController<FightAgentFL>
{
    private final GridMap<FightAgentFL> gridMap;
    
    
    private final FightAgentFL fightAgent;
    
    
    private Tile position;
    
    
    public FightAgentController(GridMap<FightAgentFL> gridMap, FightAgentFL fightAgent)
    {
        this.gridMap = gridMap;
        this.fightAgent = fightAgent;
        
        this.position=gridMap.getGraph().entrySet().stream().filter(entry -> entry.getValue() == fightAgent).findFirst().orElse(null).getKey();
    }
    
    
    public FightAgentController(GridMap<FightAgentFL> gridMap , FightAgentFL fightAgent, Tile position)
    {
        this.gridMap = gridMap;
        this.fightAgent = fightAgent;
        this.position = position;
    }
    
    
    public FightAgentFL getFightAgent()
    {
        return fightAgent;
    }
    
    
    @Override
    public Tile moveTo(Tile tile)
    {
        Tile oldTile = getPosition();
        gridMap.getGraph().put(oldTile, null);
        gridMap.getGraph().put(tile,getFightAgent());
        setPosition(tile);
        
        
        return oldTile;
    }
    
    
    @Override
    public GridMap<FightAgentFL> getGridMap()
    {
        return this.gridMap;
    }
    
    
    @Override
    public FightAgentFL getGridMovable()
    {
        return this.fightAgent;
    }
    
    
    @Override
    public Tile getPosition()
    {
        return this.position;
    }
    
    
    public void setPosition(Tile position)
    {
        this.position = position;
    }
}
