package com.gitgud.engine.model.action;

import com.gitgud.engine.control.ActionAwaiterController;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;


public interface AttackAction<AwaiterType extends ActionAwaiterController<ActionAwaiterModel<Fighter>, Fighter>> extends FromToAction<AwaiterType, Tile>
{
    @Override
    default void enAct(AwaiterType awaiter)
    {
        GridMap<Fighter> gridMap = awaiter.getModel().getGridMap();
        Tile from = getFrom();
        Tile to = getTo();
        float distance = (float) from.distance(to);
        gridMap.get(from).attack(gridMap.get(to), distance);
    }
    
}
