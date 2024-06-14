package com.gitgud.engine.model.action;

import com.gitgud.engine.model.gameObject.agent.Fighter;
import com.gitgud.engine.model.map.GridMap;
import javafx.geometry.Point2D;


public interface AttackAction<Awaiter extends ActionAwaiter<Fighter>> extends FromToAction<Awaiter, Point2D, Point2D>
{
    @Override
    default void enAct(Awaiter awaiter)
    {
        GridMap<Fighter> gridMap= awaiter.getGridMap();
        Point2D from = getFrom();
        Point2D to = getTo();
        float distance =(float) from.distance(to);
        gridMap.get(from).attack(gridMap.get(to),distance);
    }
    
}
