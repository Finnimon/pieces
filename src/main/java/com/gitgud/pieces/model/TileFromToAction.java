package com.gitgud.pieces.model;

import com.gitgud.engine.model.action.ActionAwaiter;
import com.gitgud.engine.model.action.Actor;
import com.gitgud.engine.model.map.Tile;


public abstract class TileFromToAction<ActionAwaiterType extends ActionAwaiter> implements com.gitgud.engine.model.action.FromToAction<ActionAwaiterType, Tile, Tile>
{
    private final Tile from;
    
    
    private final Tile to;
    
    
    public TileFromToAction(Tile from, Tile to)
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
}
