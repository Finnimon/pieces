package com.gitgud.engine.model.action;

import com.gitgud.engine.model.gameObject.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.GridMapRender;


public abstract class TileMovementAction<Awaiter extends ActionAwaiter> implements FromToAction<Awaiter,Tile>
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
        GridMap gridMap = awaiter.getGridMap();
        Object gridMappable = gridMap.clearVertex(from);
        gridMap.place(to, gridMappable);
    }
}
