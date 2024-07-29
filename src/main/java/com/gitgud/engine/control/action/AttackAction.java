package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.GridMapRender;


public interface AttackAction<
            AwaiterType extends ActionAwaitingController<ModelType, FighterType, RenderType>,
            ModelType extends ActionAwaiterModel<FighterType>,
            FighterType extends Fighter,
            RenderType extends ActionContextRender<ModelType, FighterType>
        > extends FromToAction<AwaiterType, Tile>
{
    @Override
    default void enAct(AwaiterType awaiter)
    {
        GridMap<FighterType> gridMap = awaiter.getModel().getGridMap();
        Tile from = getFrom();
        Tile to = getTo();
        float distance = (float) from.distance(to);
        FighterType attacked = gridMap.get(to);
        
        gridMap.get(from).attack(attacked, distance);
        
        GridMapRender<FighterType> gridMapRender = awaiter.getRender().getGridMapRender();
        
        if (!attacked.isDead())
        {
            return;
        }
        
        gridMap.clearVertex(to);
        gridMapRender.removeGridMappable(attacked);
    }
}
