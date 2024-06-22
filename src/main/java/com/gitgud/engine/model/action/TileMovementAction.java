package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaiterController;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;


public abstract class TileMovementAction<Awaiter extends ActionAwaiterController<?, ?>> implements FromToAction<Awaiter, Tile>
{
    private final Tile from;
    
    
    private final Tile to;
    
    
    public TileMovementAction(Tile from, Tile to)
    {
        this.from = from;
        this.to = to;
    }
    
    
    @Override
    public Tile getFrom()
    {
        return from;
    }
    
    
    @Override
    public Tile getTo()
    {
        return to;
    }
    
    
    @Override
    public void enAct(Awaiter awaiter)
    {
        Tile from = getFrom();
        Tile to = getTo();
        GridMap gridMap = awaiter.getModel().getGridMap();
        Object gridMappable = gridMap.clearVertex(from);
        gridMap.place(to, gridMappable);
    }
}
