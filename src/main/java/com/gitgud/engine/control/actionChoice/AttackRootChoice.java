package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.action.AttackAction;
import com.gitgud.engine.model.action.ToAction;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AttackRootChoice<ActionAwaitingType extends ActionAwaitingController<ModelType, FighterType, RenderType>, ModelType extends ActionAwaiterModel<FighterType>, FighterType extends Fighter, RenderType extends ActionContextRender<ModelType, FighterType>> extends RootToActionChoice<ActionAwaitingType, ModelType, FighterType, RenderType>
{
    
    
    public static final String NAME = "Attack";
    
    
    public static final String DESCRIPTION = "Attack another fighter";
    
    
    public AttackRootChoice(String name, String description, ActionAwaitingType awaiter,
                            List<ToActionChoice<ActionAwaitingType, ModelType, FighterType, RenderType>> toActionChoices)
    {
        super(name, description, awaiter, toActionChoices);
    }
    
    
    public AttackRootChoice(ActionAwaitingType fightController,FighterType fighterType)
    {
        super(NAME, DESCRIPTION, fightController, toActionChoices(fightController, fighterType));
    }
    
    
    public static <AAType extends ActionAwaitingController<MType, FType, RType>, MType extends ActionAwaiterModel<FType>, FType extends Fighter, RType extends ActionContextRender<MType, FType>> List<ToActionChoice<AAType, MType, FType, RType>> toActionChoices(
            AAType actionAwaiter, FType fighterType)
    {
        List<ToActionChoice<AAType, MType, FType, RType>> toActionChoices = new ArrayList<>();
        Tile position=actionAwaiter.getActivePosition();
        Collection<Tile> attackableTiles = fighterType.findAttackableTiles(actionAwaiter.getModel().getGridMap(), position);
        
        for (Tile attackableTile: attackableTiles
             )
        {
            ToAction<AAType,Tile> action = new AttackAction<AAType, MType, FType, RType>()
            {
                @Override
                public Tile getTo()
                {
                    return attackableTile;
                }
                
                
                @Override
                public Tile getFrom()
                {
                    return position;
                }
            };
            
            
            toActionChoices.add(new ToActionChoice<>(NAME,DESCRIPTION,actionAwaiter,action));
        }
        
        return toActionChoices;
    }
}
