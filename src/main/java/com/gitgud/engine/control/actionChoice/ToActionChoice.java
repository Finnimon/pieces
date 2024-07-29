package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.control.action.AttackAction;
import com.gitgud.engine.control.action.TileMovementAction;
import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import javafx.scene.Node;
import javafx.scene.paint.Color;


public class ToActionChoice<AwaitingType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>> extends
                                                                                                                                                                                                                                                                                        ActionChoice<AwaitingType, ModelType, GridMappableType, RenderType>
{
    public ToActionChoice(String name, String description, AwaitingType awaiter, ToAction<AwaitingType, Tile> action)
    {
        super(name, description, awaiter, action);
    }
    
    
    private static <AwaitingType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>> Color determineHighlightColor(
            ToAction<AwaitingType, Tile> action)
    {
        Color color = Color.BLUE;
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
    
    
    @Override
    protected ToAction<AwaitingType, Tile> getAction()
    {
        return (ToAction<AwaitingType, Tile>) super.getAction();
    }
    
    
    @Override
    public void show(AwaitingType actionAwaiter)
    {
        ToAction<AwaitingType, Tile> action = getAction();
        
        Color color = determineHighlightColor(action);
        RenderType render = actionAwaiter.getRender();
        Node node = render.getGridMapRender().addHighLight(action.getTo(), color, getMouseEventHandler());
        render.getHud().registerChoice(node);
    }
}
