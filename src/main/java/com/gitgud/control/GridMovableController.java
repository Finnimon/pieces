package com.gitgud.control;

import com.gitgud.model.gameObjects.gridMovable.Agent;
import com.gitgud.model.map.GridMapContext;
import com.gitgud.model.map.GridMapping;
import com.gitgud.model.map.Tile;


public class GridMovableController
{
    private final GridMapContext gridMapContext;
    
    
    private final GridMapping gridMapping;
    
    
    public GridMovableController(GridMapContext gridMapContext, GridMapping gridMapping)
    {
        this.gridMapContext = gridMapContext;
        this.gridMapping = gridMapping;
    }
    
    
    public GridMovableController(GridMapContext gridMapContext, Agent agent)
    {
        this(gridMapContext, new GridMapping(agent, (Tile) gridMapContext.getGridMappings().get(agent)));
    }
    
    
    public GridMapContext getGridMapContext()
    {
        return gridMapContext;
    }
    
    
    public GridMapping getGridMapping()
    {
        return gridMapping;
    }
    
    
    public Tile moveTo(Tile tile)
    {
        Tile oldTile = gridMapping.setValue(tile);
        getGridMapContext().getGridMappings().put(gridMapping.getKey(), gridMapping.getValue());
        
        
        return oldTile;
    }
    
    
    private boolean canMoveTo(Tile tile)
    {
        return false;
    }
}
