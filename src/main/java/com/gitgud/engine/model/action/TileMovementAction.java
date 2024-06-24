package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;


public class TileMovementAction<Awaiter extends ActionAwaitingController<ModelType, GridMappableType,RenderType>,ModelType extends ActionAwaiterModel<GridMappableType>,GridMappableType extends GridMappable,RenderType extends ActionContextRender<ModelType, GridMappableType>> implements FromToAction<Awaiter, Tile>
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
        awaiter.getRender().getGridMapRender().relocateGridMappable(
                awaiter.getModel().getGridMap().moveElement(from, to), to);
    }
}
