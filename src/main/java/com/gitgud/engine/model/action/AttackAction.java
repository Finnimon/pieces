package com.gitgud.engine.model.action;

import com.gitgud.engine.model.gameObject.agent.Fighter;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import javafx.geometry.Point2D;


public interface AttackAction<Awaiter extends ActionAwaiter<Fighter>,Point extends Point2D> extends FromToAction<Awaiter, Point>
{
    @Override
    default void enAct(Awaiter awaiter)
    {
        GridMap<Fighter> gridMap = awaiter.getGridMap();
        Point from = getFrom();
        Point to = getTo();
        float distance = (float) from.distance(to);
        gridMap.get((Tile) from).attack(gridMap.get((Tile) to), distance);
    }
    
}
