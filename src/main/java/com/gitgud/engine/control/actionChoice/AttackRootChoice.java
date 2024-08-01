package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.action.AttackAction;
import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * RootChoice that contains multiple ToActionChoices which all contain {@link AttackAction}s
 *
 * @param <AaType> The type of the {@link ActionAwaitingController} this Action can be applied to.
 * @param <MType>  The type of the {@link ActionAwaiterModel} this Action can be applied to.
 * @param <FType>  The type of the {@link Fighter} this Action can be applied to.
 * @param <RType>  The type of the {@link ActionContextRender} this Action can be applied to.
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @see RootToActionChoice
 * @since 28.06.2024
 */
public class AttackRootChoice<AaType extends ActionAwaitingController<MType, FType, RType>
        , MType extends ActionAwaiterModel<FType>, FType extends Fighter, RType extends ActionContextRender<MType,
        FType>>
        extends RootToActionChoice<AaType, MType, FType, RType>
{
    public static final String NAME = "Attack";
    
    
    public static final String DESCRIPTION = "Attack another fighter";
    
    
    /**
     * Creates a new AttackRootChoice where all possible {@link ToActionChoice}s with {@link AttackAction}s are
     * determined.
     *
     * @param fightController the targeted {@link ActionAwaitingController}.
     * @param fighter         The currently active {@link Fighter} that will attack.
     * @Precondition: {@code fightController} and {@code fighter} must not be null.
     * The {@code fighter} is in the {@code fightController}'s {@link ActionAwaiterModel#getGridMap()}.
     * @Postcondition: A functioning {@link AttackRootChoice} is created without any exceptions being thrown.
     */
    public AttackRootChoice(@NotNull AaType fightController, @NotNull FType fighter)
    {
        super(NAME, DESCRIPTION, fightController, determineChoices(fightController, fighter));
    }
    
    
    /**
     * Determines all possible {@link ToActionChoice}s with {@link AttackAction}s and returns them.
     *
     * @param actionAwaiter The targeted {@link ActionAwaitingController}.
     * @param fighter       The currently active {@link Fighter} that will attack.
     * @return All possible {@link ToActionChoice}s with {@link AttackAction}s
     * @Precondition: The {@code fighters} is in the {@code actionAwaiter}'s {@link ActionAwaiterModel#getGridMap()}.
     * @Postcondition: All possible {@link ToActionChoice}s with {@link AttackAction}s are returned.
     */
    private static <AAType extends ActionAwaitingController<MType, FType, RType>,
            MType extends ActionAwaiterModel<FType>, FType extends Fighter, RType extends ActionContextRender<MType,
            FType>> @NotNull List<ToActionChoice<AAType, MType, FType, RType>> determineChoices(
            AAType actionAwaiter, FType fighter)
    {
        List<ToActionChoice<AAType, MType, FType, RType>> toActionChoices = new ArrayList<>();
        Tile position = actionAwaiter.getActivePosition();
        Collection<Tile> attackableTiles = fighter.findAttackableTiles(actionAwaiter.getModel().getGridMap(), position);
        
        for (Tile attackableTile : attackableTiles)
        {
            ToAction<AAType, Tile> action = new AttackAction<>()
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
            
            
            toActionChoices.add(new ToActionChoice<>(NAME, DESCRIPTION, actionAwaiter, action));
        }
        
        return toActionChoices;
    }
}
