package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;


public interface AttackAction<FighterType extends com.gitgud.engine.model.gameobjects.agent.Fighter,AwaiterType extends ActionAwaitingController<ActionAwaiterModel<FighterType>, FighterType, ActionContextRender<ActionAwaiterModel<FighterType>, FighterType>>> extends FromToAction<AwaiterType, Tile>
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
        if (!attacked.isDead())
        {
            return;
        }
        gridMap.clearVertex(to);
        awaiter.getRender().getGridMapRender().removeGridMappable(attacked);
    }
    
}
