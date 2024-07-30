package com.gitgud.pieces.control;

import com.gitgud.engine.control.GridMovableController;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


/**
 * Controller for {@link com.gitgud.pieces.model.gameobjects.agents.FightAgent}
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
        
        position = gridMap.getVertex(fightAgent);
    }
    
    
    public FightAgentController(GridMap<FightAgent> gridMap, FightAgent fightAgent, Tile position)
    {
        this.gridMap = gridMap;
        this.fightAgent = fightAgent;
        this.position = position;
    }
    
    
    @Override
    public Tile moveTo(Tile tile)
    {
        Tile oldTile = getPosition();
        gridMap.place(oldTile, null);
        gridMap.place(tile, getFightAgent());
        setPosition(tile);
        //todo update gui method
        
        return oldTile;
    }
    
    
    public FightAgent getFightAgent()
    {
        return fightAgent;
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
