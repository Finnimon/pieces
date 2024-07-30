package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.Applicable;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.graph.Point2D;


/**
 * A Targeted {@link ToAction} that has an {@link Applicable}
 *
 * @param <ApplicableType>
 * @param <GMType>
 * @param <MType>
 * @param <RType>
 * @param <AAType>> The type of the {@link ActionAwaitingController} this Action can be applied to
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public interface ApplicableToAction<AAType extends ActionAwaitingController<MType, GMType, RType>, ApplicableType extends Applicable<GMType>, GMType extends GridMappable, MType extends ActionAwaiterModel<GMType>, RType extends ActionContextRender<MType, GMType>> extends
                                                                                                                                                                                                                                                                       ToAction<AAType, Tile>
{
    /**
     * Will apply the {@link Applicable} to the {@link GMType} at {@link #getTo()}
     * @param awaiter the {@link ActionAwaitingController} this Action will be enacted upon
     */
    @Override
    default void enAct(AAType awaiter)
    {
        GridMap<GMType> gridMap = awaiter.getModel().getGridMap();
        getApplicable().apply(gridMap.get(getTo()));
    }
    
    
    /**
     * Gets the {@link Applicable} of this Action
     * @return the {@link Applicable}
     */
    ApplicableType getApplicable();
}
