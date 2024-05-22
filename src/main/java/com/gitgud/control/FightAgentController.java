package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.Tile;


public class FightAgentController extends GridMovableController<FightAgent>
{
    private final GridMap<FightAgent> gridMap;
    
    
    private final FightAgent fightAgent;
    
    
    private Tile position;
    
    
    public FightAgentController(GridMap<FightAgent> gridMap, FightAgent fightAgent)
    {
        this.gridMap = gridMap;
        this.fightAgent = fightAgent;
        
        this.position=gridMap.entrySet().stream().filter(entry -> entry.getValue() == fightAgent).findFirst().orElse(null).getKey();
    }
    
    
    public FightAgentController(GridMap<FightAgent> gridMap ,FightAgent fightAgent, Tile position)
    {
        this.gridMap = gridMap;
        this.fightAgent = fightAgent;
        this.position = position;
    }
    
    
    public FightAgent getFightAgent()
    {
        return fightAgent;
    }
    
    
    @Override
    public Tile moveTo(Tile tile)
    {
        Tile oldTile = getPosition();
        gridMap.put(oldTile, null);
        gridMap.put(tile,getFightAgent());
        setPosition(tile);
        
        
        return oldTile;
    }
    
    
    @Override
    public GridMap<FightAgent> getGridMap()
    {
        return this.gridMap;
    }
    
    
    @Override
    public FightAgent getGridMovable()
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
