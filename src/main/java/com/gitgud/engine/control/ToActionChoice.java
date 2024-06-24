package com.gitgud.engine.control;

import com.gitgud.engine.model.action.*;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import javafx.scene.paint.Color;


public final class ToActionChoice<AwaitingType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>,ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType,GridMappableType>> extends ActionChoice<AwaitingType, ModelType, GridMappableType, RenderType>
{
    public ToActionChoice(String name, String description,AwaitingType awaiter,  ToAction<AwaitingType, Tile> action)
    {
        super(name, description,awaiter,  action);
    }
    
    
    @Override
    protected ToAction<AwaitingType,Tile> getAction()
    {
        return (ToAction<AwaitingType, Tile>) super.getAction();
    }
    
    
    @Override
    public void show(AwaitingType actionAwaiter)
    {
        ToAction<AwaitingType,Tile> action = getAction();
        
        Color color = determineHighlightColor(action);
        
        actionAwaiter.getRender().getGridMapRender().addHighLight(action.getTo(),color,getMouseEventHandler());
    }
    
    
    private static <AwaitingType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType,GridMappableType>> Color determineHighlightColor(
            ToAction<AwaitingType, Tile> action)
    {
        Color color =Color.BLUE;
        if (action instanceof AttackAction)
        {
            color = Color.RED;
        }
        else if (action instanceof TileMovementAction)
        {
            color = Color.GREEN;
        }
        return color;
    }
}
