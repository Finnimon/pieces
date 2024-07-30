package com.gitgud.engine.control.action;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.Applicable;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;


/**
 * A Targeted {@link ToAction} that has an {@link Applicable}
 *
 * @param <ApplicableType>
 * @param <GmType>
 * @param <MType>
 * @param <RType>
 * @param <AaType>>        The type of the {@link ActionAwaitingController} this Action can be applied to
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public interface ApplicableToAction<AaType extends ActionAwaitingController<MType, GmType, RType>,
        ApplicableType extends Applicable<GmType>, GmType extends GridMappable,
        MType extends ActionAwaiterModel<GmType>, RType extends ActionContextRender<MType, GmType>>
        extends ToAction<AaType, Tile>
{
    /**
     * Will apply the {@link Applicable} to the {@link GmType} at {@link #getTo()}
     *
     * @param awaiter the {@link ActionAwaitingController} this Action will be enacted upon
     */
    @Override
    default void enAct(AaType awaiter)
    {
        GridMap<GmType> gridMap = awaiter.getModel().getGridMap();
        getApplicable().apply(gridMap.get(getTo()));
    }
    
    
    /**
     * Gets the {@link Applicable} of this Action
     *
     * @return the {@link Applicable}
     */
    ApplicableType getApplicable();
}
