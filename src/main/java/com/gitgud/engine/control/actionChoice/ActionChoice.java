package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.action.Action;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.view.ActionChoiceRender;
import com.gitgud.engine.view.ActionContextRender;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;


/**
 * <p>Actions are contained by this class. It is used to structure possible Actions in the game as a tree.
 * <p>This class can act as a Leaf and is extended for parent functionalities
 *
 * @param <AaType>
 * @param <MType>
 * @param <GmType>
 * @param <RType>
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @see RootChoice
 * @see Action
 * @see RootActionChoice
 * @see ActionChoiceRender
 * @see ActionAwaitingController
 * @since 28.06.2024
 */
public abstract class ActionChoice<AaType extends ActionAwaitingController<MType, GmType, RType>,
        MType extends ActionAwaiterModel<GmType>, GmType extends GridMappable,
        RType extends ActionContextRender<MType, GmType>>
        implements Named, Describable
{
    /**
     * The name of the Action
     */
    private final String name;
    
    
    /**
     * The description of the Action
     */
    private final String description;
    
    
    /**
     * The targeted {@link ActionAwaitingController}
     */
    private final AaType awaiter;
    
    
    /**
     * The action to be enacted if it is chosen or null to indicate a rootAction
     */
    private final Action<AaType> action;
    
    
    public ActionChoice(@NotNull String name, @NotNull String description, @NotNull AaType awaiter)
    {
        this(name, description, awaiter, null);
    }
    
    
    public ActionChoice(@NotNull String name, @NotNull String description, @NotNull AaType awaiter,
                        Action<AaType> action)
    {
        this.name = name;
        this.awaiter = awaiter;
        this.description = description;
        this.action = action;
    }
    
    
    public static <AAType extends ActionAwaitingController<MType, GType, RType>,
            MType extends ActionAwaiterModel<GType>, GType extends GridMappable,
            RType extends ActionContextRender<MType, GType>> @NotNull ActionChoice<AAType, MType, GType, RType> empty(
            @NotNull String name, @NotNull String description, @NotNull AAType actionAwaiter)
    {
        return new ActionChoice<>(name, description, actionAwaiter, Action.empty())
        {
            @Override
            public void show(@NotNull AAType actionAwaiter)
            {
                throw new UnsupportedOperationException(
                        "This should never be called for ActionChoice.empty() Choice, \n\r as it is simply meant to " +
                        "do nothing but advance the awaiter."); //should never be called because the action is not null
            }
        };
    }
    
    
    /**
     * Creates and returns an ActionChoice that returns to the absolute current {@link RootChoice} of the game
     * @param name The name for the returned action
     * @param description The description for the returned action
     * @param actionAwaiter The targeted {@link ActionAwaitingController}
     * @return The {@link ActionChoice} that returns to the absolute {@link RootChoice}
     */
    public static <AAType extends ActionAwaitingController<MType, GType, RType>,
            MType extends ActionAwaiterModel<GType>, GType extends GridMappable,
            RType extends ActionContextRender<MType, GType>> @NotNull ActionChoice<AAType, MType, GType, RType> returnToRoot(
            @NotNull String name, @NotNull String description, @NotNull AAType actionAwaiter)
    {
        return new ActionChoice<>(name, description, actionAwaiter, Action.rootReturn())
        {
            @Override
            public void show(@NotNull AAType actionAwaiter)
            {
                throw new UnsupportedOperationException(
                        "This should never be called for ActionChoice.rootReturn() Choice, \n\r as it is simply meant" +
                        " to " +
                        "do nothing but advance the awaiter."); //should never be called because the action is not null
            }
        };
    }
    
    
    /**
     * Getter for the {@link #action} of this {@link ActionChoice}
     *
     * @return {@link #action}
     */
    protected Action<AaType> getAction()
    {
        return action;
    }
    
    
    /**
     * Creates and returns a usable {@link Node} for the {@link ActionChoice}
     *
     * @return A {@link ActionChoiceRender} of this {@link ActionChoice}
     */
    public @NotNull ActionChoiceRender getNode()
    {
        return new ActionChoiceRender(this);
    }
    
    
    /**
     * @inheritDoc
     */
    @Override
    public @NotNull String description()
    {
        return this.description;
    }
    
    
    /**
     * @inheritDoc
     */
    @Override
    public @NotNull String name()
    {
        return this.name;
    }
    
    
    /**
     * Creates an EventHandler for the {@link ActionChoiceRender}
     *
     * @return an EventHandler that can be added to the {@link ActionChoiceRender}
     */
    public @NotNull EventHandler<MouseEvent> getMouseEventHandler()
    {
        return event ->
        {
            if (event == null)
            {
                return;
            }
            if (event.getEventType() != MouseEvent.MOUSE_CLICKED)
            {
                return;
            }
            if (event.getButton() != MouseButton.PRIMARY)
            {
                return;
            }
            
            event.consume();
            select();
        };
    }
    
    
    /**
     * <p>Selects this action choice.
     * <p>If this is a rootChoice, it's choices will be displayed.
     * <p>Else {@code action.enAct(awaiter)} will be called.
     */
    public void select()
    {
        AaType awaiter = getAwaiter();
        awaiter.getRender().getHud().clearChoices();
        
        if (action != null)
        {
            action.enAct(awaiter);
            awaiter.advance();
            return;
        }
        
        show(awaiter);
    }
    
    
    /**
     * Getter for the {@link ActionAwaitingController} associated with this {@link ActionChoice}
     *
     * @return the {@link ActionAwaitingController} associated with this {@link ActionChoice}
     */
    public AaType getAwaiter()
    {
        return awaiter;
    }
    
    
    /**
     * <p>If this is a rootChoice, it's choices will be displayed.
     * <p>Else it's Node will be shown in the {@link com.gitgud.engine.view.ActionContextHud}
     *
     * @param actionAwaiter the {@link ActionAwaitingController} associated with this {@link ActionChoice}
     */
    public abstract void show(@NotNull AaType actionAwaiter);
}
