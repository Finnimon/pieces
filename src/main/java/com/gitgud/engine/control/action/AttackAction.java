package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.GridMapRender;


/**
 * Implements {@link FromToAction} to attack a {@link Tile} on the {@link GridMap}
 *
 * @param <AaType>
 * @param <MType>
 * @param <FType>
 * @param <RType>
 * @see Fighter
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public interface AttackAction<AaType extends ActionAwaitingController<MType, FType, RType>,
        MType extends ActionAwaiterModel<FType>, FType extends Fighter, RType extends ActionContextRender<MType, FType>>
        extends FromToAction<AaType, Tile>
{
    /**
     * Makes the {@link Fighter} at {@link #getFrom()} attack the {@link Fighter} at {@link #getTo()}.
     *
     * @param awaiter the {@link ActionAwaitingController} this Action will be enacted upon
     */
    @Override
    default void enAct(AaType awaiter)
    {
        GridMap<FType> gridMap = awaiter.getModel().getGridMap();
        Tile from = getFrom();
        Tile to = getTo();
        float distance = (float) from.distance(to);
        FType attacked = gridMap.get(to);
        
        gridMap.get(from).attack(attacked, distance);
        
        GridMapRender<FType> gridMapRender = awaiter.getRender().getGridMapRender();
        
        if (!attacked.isDead())
        {
            return;
        }
        
        gridMap.clearVertex(to);
        gridMapRender.removeGridMappable(attacked);
    }
}
