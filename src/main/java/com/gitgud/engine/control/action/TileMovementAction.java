package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;


/**
 * Default implementation for Moving {@link com.gitgud.engine.model.gameobjects.GridMovable}s on a
 * {@link com.gitgud.engine.model.map.GridMap}
 *
 * @param <AaType> The type of the {@link ActionAwaitingController} this Action can be applied to.
 * @param <MType>  The type of the {@link ActionAwaiterModel} this Action can be applied to.
 * @param <GmType> The type of the {@link GridMappable} this Action can be applied to.
 * @param <RType> The type of the {@link ActionContextRender} this Action can be applied to.
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public class TileMovementAction<AaType extends ActionAwaitingController<MType, GmType, RType>,
        MType extends ActionAwaiterModel<GmType>, GmType extends GridMappable,
        RType extends ActionContextRender<MType, GmType>>
        implements FromToAction<AaType, Tile>
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
    public synchronized void enAct(AaType awaiter)
    {
        awaiter.getRender()
               .getGridMapRender()
               .relocateGridMappable(awaiter.getModel().getGridMap().moveElement(from, to), to);
    }
}
