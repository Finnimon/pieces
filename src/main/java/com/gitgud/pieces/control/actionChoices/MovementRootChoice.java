package com.gitgud.pieces.control.actionChoices;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.action.TileMovementAction;
import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.control.actionChoice.ToActionChoice;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.GridMovable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.pieces.control.FightController;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.control.action.MissionMovementAction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Allows for creation of the RootChoice for
 *
 * @param <AaType> The type of the {@link ActionAwaitingController} this RootChoice can be applied to.
 * @param <MType>  The type of the {@link ActionAwaiterModel} {@link AaType} requires.
 * @param <GmType> The type of the {@link GridMappable} {@link MType} requires.
 * @param <RType>  The type of the {@link ActionContextRender} {@link MType} requires.
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class MovementRootChoice<AaType extends ActionAwaitingController<MType, GmType, RType>,
        MType extends ActionAwaiterModel<GmType>, GmType extends GridMappable,
        RType extends ActionContextRender<MType, GmType>>
        extends RootToActionChoice<AaType, MType, GmType, RType>
{
    /**
     * The default name of this RootChoice
     */
    private static final String NAME = "Move";
    
    
    /**
     * The default description of this RootChoice
     */
    private static final String DESCRIPTION = "Move the active character to a new position.";
    
    
    /**
     * Determines all possible movement choices for the given {@link GridMovable} and {@link Tile} and calls
     * super constructor.
     *
     * @param awaiter     The {@link ActionAwaitingController} this RootChoice will be enacted upon.
     * @param gridMovable The currently active {@link GridMovable}.
     * @param position    The {@link Tile} to start from.
     * @throws IllegalArgumentException If this RootChoice is used with any other {@link ActionAwaitingController} than
     *                                  {@link FightController} or {@link MissionController}.
     * @Precondition: May only be used with a {@link FightController} or {@link MissionController}.
     * @Postcondition: No Exceptions will be thrown and the RootChoice will be created.
     */
    public MovementRootChoice(AaType awaiter, GridMovable gridMovable, Tile position)
    {
        super(NAME, DESCRIPTION, awaiter, toActionChoices(gridMovable, position, awaiter));
        if (!(awaiter instanceof FightController) && !(awaiter instanceof MissionController))
        {
            throw new IllegalArgumentException(
                    "MovementRootChoice can only be used with a FightController or MissionController");
        }
    }
    
    
    /**
     * Finds all possible movement choices for the given {@link GridMovable} and {@link Tile} on the given
     * {@link ActionAwaitingController}'s model's GridMap.
     *
     * @param actionAwaiter The {@link ActionAwaitingController} this RootChoice will be enacted upon.
     * @param gridMovable   The currently active {@link GridMovable}.
     * @param position      The {@link Tile} to start from.
     * @return All possible movement choices for the given {@link GridMovable} and {@link Tile}.
     */
    private static <AAType extends ActionAwaitingController<MType, GMType, RType>,
            MType extends ActionAwaiterModel<GMType>, GMType extends GridMappable,
            RType extends ActionContextRender<MType, GMType>> List<ToActionChoice<AAType, MType, GMType, RType>> toActionChoices(
            GridMovable gridMovable, Tile position, AAType actionAwaiter)
    {
        Collection<Tile> inRangeTiles = gridMovable.findPossibleMovementTargets(actionAwaiter.getModel().getGridMap(),
                                                                                position);
        List<ToActionChoice<AAType, MType, GMType, RType>> toActionChoices = new ArrayList<>();
        
        for (Tile to : inRangeTiles)
        {
            toActionChoices.add(createMovementChoice(position, to, actionAwaiter));
        }
        
        return toActionChoices;
    }
    
    
    /**
     * Creates and returns a valid {@link ToActionChoice} for moving the given {@link GridMovable} from {@code
     * position} to
     * {@code to}.
     *
     * @param position      The current position of the {@link GridMovable}.
     * @param to            The target of the expected movement Choice.
     * @param actionAwaiter The {@link ActionAwaitingController} the resulting {@link ToActionChoice} can be enacted
     *                      upon.
     * @return A valid {@link ToActionChoice} for moving the given {@link GridMovable} from {@code position} to the
     * given {@code to}.
     */
    @NotNull
    private static <AAType extends ActionAwaitingController<MType, GMType, RType>,
            MType extends ActionAwaiterModel<GMType>, GMType extends GridMappable,
            RType extends ActionContextRender<MType, GMType>> ToActionChoice<AAType, MType, GMType, RType> createMovementChoice(
            Tile position, Tile to, AAType actionAwaiter)
    {
        ToAction<AAType, Tile> action = createAction(position, to, actionAwaiter);
        
        if (!(actionAwaiter instanceof FightController))
        {
            return new ToActionChoice<>(NAME, DESCRIPTION, actionAwaiter, action);
        }
        
        return (ToActionChoice<AAType, MType, GMType, RType>) new FightMovementChoice((FightController) actionAwaiter,
                                                                                      (ToAction<FightController,
                                                                                              Tile>) action);
    }
    
    
    /**
     * Creates and returns a valid {@link ToAction} for moving the given {@link GridMovable} from {@code position} to
     * {@code to}.
     *
     * @param position      The current position of the {@link GridMovable}.
     * @param to            The target of the expected movement action.
     * @param actionAwaiter The {@link ActionAwaitingController} the resulting {@link ToActionChoice} can be enacted
     *                      upon.
     * @return A valid {@link TileMovementAction} for moving the given {@link GridMovable} from {@code position} to
     * the given {@code to}.
     */
    @NotNull
    private static <AAType extends ActionAwaitingController<MType, GMType, RType>,
            MType extends ActionAwaiterModel<GMType>, GMType extends GridMappable,
            RType extends ActionContextRender<MType, GMType>> ToAction<AAType, Tile> createAction(
            Tile position, Tile to, AAType actionAwaiter)
    {
        if (!(actionAwaiter instanceof MissionController))
        {
            return new TileMovementAction<>(position, to);
        }
        return (ToAction<AAType, Tile>) new MissionMovementAction(position, to);
    }
}
