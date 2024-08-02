package com.gitgud.engine.control;

import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.utility.Strings;
import com.gitgud.engine.view.ActionContextRender;
import javafx.concurrent.Task;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


/**
 * <p>This Class offers a framework for user interaction with {@link ActionChoice}s in the game.
 * <p>This Controller is used to wait offer the user {@link ActionChoice}s via the
 * {@link com.gitgud.engine.view.ActionContextHud}.
 * <p>It is meant to be used for in-game situations, where the user is in control of the game and the gameplay is
 * based on a GridMap and ActionChoices.
 *
 * @param <MType>  The type of the {@link ActionAwaiterModel} used by this Controller.
 * @param <GmType> The type of the {@link GridMappable} on this {@link ActionAwaiterModel}s GridMap.
 * @param <RType>  The type of Render for this Controller.
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 28.06.2024
 * @Version: 1.1
 * @see com.gitgud.engine.control.action.Action
 * @see ActionChoice
 */
public abstract class ActionAwaitingController<MType extends ActionAwaiterModel<GmType>, GmType extends GridMappable,
        RType extends ActionContextRender<MType, GmType>>
        extends Controller<MType, RType> implements Ending, Startable
{
    /**
     * Default Constructor that assigns the given model and render to this Controller.
     *
     * @param model  The model used by this Controller.
     * @param render The render used by this Controller.
     */
    public ActionAwaitingController(@NotNull MType model, @NotNull RType render)
    {
        super(model, render);
    }
    
    
    @Override
    public void advance()
    {
        if (tryEnd())
        {
            return;
        }
        getModel().incrementTurn();
        getRender().getGridMapRender().clearHighLights();
        showRootAction();
        hightlightActivePosition();
        getRender().updateRender();
    }
    
    
    /**
     * Shows the current absolute RootChoice {@link ActionChoice} to the user.
     */
    public void showRootAction()
    {
        executeActionChoiceTask(ActionChoice::select);
    }
    
    
    /**
     * Highlights the currently active Tile.
     */
    protected void hightlightActivePosition()
    {
        //todo fix currently not working as it blocks the InfoPane event for the active Agent underneath the highlight
        Tile tile = getActivePosition();
        if (tile == null)
        {
            return;
        }
        getRender().getGridMapRender().addHighLight(tile);
    }
    
    
    /**
     * Determines the current absolute RootChoice {@link ActionChoice} on a worker Thread {@link Task()}
     * and assigns
     * {@code onSucceeded} to it.
     *
     * @param onSucceeded The {@link Consumer} that will accept the absolute RootChoice when the {@link Task} is
     *                    finished.
     * @Precondition: {@link #getActionChoice()} is properly overridden.
     * @Postcondition: The {@link Consumer} will be called with the absolute RootChoice when the {@link Task} is
     * finished. The Framework will function correctly.
     * @see #actionChoiceTask()
     */
    protected final void executeActionChoiceTask(@NotNull Consumer<ActionChoice> onSucceeded)
    {
        Task<ActionChoice> task = actionChoiceTask();
        task.setOnSucceeded(x -> onSucceeded.accept(task.getValue()));
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(task);
        exec.shutdown();
    }
    
    
    /**
     * Determines the Tile which the active Agent is mapped to.
     *
     * @return The Tile which the active Agent is mapped to.
     */
    public abstract Tile getActivePosition();
    
    
    /**
     * Creates a Task that determines the absolute RootChoice {@link ActionChoice} by calling
     * {@link #getActionChoice()} and returns it.
     *
     * @return The created Task.
     * @see #getActionChoice()
     */
    private Task<ActionChoice> actionChoiceTask()
    {
        return new Task<>()
        {
            @Override
            protected ActionChoice call()
            {
                return getActionChoice();
            }
        };
    }
    
    
    /**
     * <p>This method is the core of the ActionAwaitingController.
     * <p>If it is now overridden in a subclass, the Framework will not work.
     *
     * @param <AAType> The type of Subclass of {@link ActionAwaitingController}
     * @return The current absolute RootChoice {@link ActionChoice}.
     */
    public abstract <AAType extends ActionAwaitingController<MType, GmType, RType>> @NotNull ActionChoice<AAType,
            MType, GmType, RType> getActionChoice();
    
    
    @Override
    public void start()
    {
        hightlightActivePosition();
        getRender().show();
        showRootAction();
    }
    
    
    /**
     * Creates and Returns an ActionChoice that skips the current turn. and will cause the ActionAwaitingController
     * to advance without showing any enacting any {@link com.gitgud.engine.control.action.Action}s.
     *
     * @return An {@link ActionChoice} that skips the current turn.
     */
    protected final <AAType extends ActionAwaitingController<MType, GmType, RType>> ActionChoice<AAType, MType,
            GmType, RType> getSkipTurnChoice()
    {
        return (ActionChoice<AAType, MType, GmType, RType>) ActionChoice.empty("Skip" + Strings.LINE_BREAK + "Turn",
                                                                               "Skip this turn.",
                                                                               this);
    }
}
