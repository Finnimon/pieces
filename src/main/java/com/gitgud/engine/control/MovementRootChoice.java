package com.gitgud.engine.control;

import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.action.TileMovementAction;
import com.gitgud.engine.model.action.ToAction;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.GridMovable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.mission.MissionMovementAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class MovementRootChoice<ActionAwaitingType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable,RenderType extends ActionContextRender<ModelType,GridMappableType>> extends RootToActionChoice<ActionAwaitingType, ModelType, GridMappableType,RenderType>
{
    private static final String NAME = "Move";
    private static final String DESCRIPTION = "Move the active character to a new position.";
    
    public MovementRootChoice(ActionAwaitingType awaiter, GridMovable gridMovable, Tile position)
    {
        super(NAME, DESCRIPTION, awaiter, toActionChoices(gridMovable, position, awaiter));
    }
    
    private static
    <AAType extends ActionAwaitingController<MType, GMType, RType>, MType extends ActionAwaiterModel<GMType>, GMType extends GridMappable, RType extends ActionContextRender<MType,GMType>>
    List<ToActionChoice<AAType, MType, GMType,RType>>
    toActionChoices(GridMovable gridMovable, Tile position, AAType actionAwaiter)
    {
        GridMap<GMType> gridMap=actionAwaiter.getModel().getGridMap();
        Collection<Tile> inRangeTiles= gridMovable.getInRangeTiles(gridMap, position);
        List<ToActionChoice<AAType, MType, GMType,RType>> toActionChoices=new ArrayList<>();
        
        for (Tile to: inRangeTiles)
        {
            ToAction<AAType, Tile> action;
            if (actionAwaiter instanceof MissionController)
            {
                action = (ToAction<AAType, Tile>) new MissionMovementAction(position, to);
            }
            else
            {
                action = (ToAction<AAType, Tile>) new TileMovementAction<>(position, to);//todo
            }
            toActionChoices.add(new ToActionChoice<>(NAME, DESCRIPTION, actionAwaiter ,action));
            
        }
        
        
        return toActionChoices;
    }
}
