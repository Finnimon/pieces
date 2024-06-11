package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;


/**
 * Controller for {@link com.gitgud.model.gameObjects.gridMovable.FightAgent}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class FightAgentController extends GridMovableController<FightAgent>
{
    private final GridMap<FightAgent> gridMap;
    
    
    private final FightAgent fightAgent;
    
    
    private Tile position;
    
    
    public FightAgentController(FightAgent fightAgent)
    {
        this.gridMap = ActiveGameController.getInstance().get().getFight().getGridMap();
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
        //todo update gui method
        
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
