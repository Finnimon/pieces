package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.control.action.Action;
import com.gitgud.engine.control.action.AttackAction;
import com.gitgud.engine.control.action.TileMovementAction;
import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.engine.view.ActionContextRender;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;


/**
 * A targeted ActionChoice whose {@link com.gitgud.engine.control.action.Action} is a {@link ToAction}.
 *
 * @param <AaType> The type of the {@link ActionAwaitingController} this Action can be applied to.
 * @param <MType>  The type of the {@link ActionAwaiterModel} this Action can be applied to.
 * @param <GmType> The type of the {@link GridMappable} this Action can be applied to.
 * @param <RType>  The type of the {@link ActionContextRender} this Action can be applied to.
 */
public class ToActionChoice<AaType extends ActionAwaitingController<MType, GmType, RType>,
        MType extends ActionAwaiterModel<GmType>, GmType extends GridMappable,
        RType extends ActionContextRender<MType, GmType>>
        extends ActionChoice<AaType, MType, GmType, RType>
{
    public ToActionChoice(@NotNull String name, @NotNull String description, @NotNull AaType awaiter,
                          @NotNull ToAction<AaType, Tile> action)
    {
        super(name, description, awaiter, action);
    }
    
    
    /**
     * Shows this {@link ActionChoice} on a {@link com.gitgud.engine.view.GridMapRender} as a clickable highlight.
     *
     * @param actionAwaiter the {@link ActionAwaitingController} associated with this {@link ActionChoice}
     */
    @Override
    public void show(@NotNull AaType actionAwaiter)
    {
        ToAction<AaType, Tile> action = getAction();
        
        Color color = determineHighlightColor();
        RType render = actionAwaiter.getRender();
        Node node = render.getGridMapRender().addHighLight(action.getTo(), color, getMouseEventHandler());
        render.getHud().registerChoice(node);
    }
    
    
    /**
     * Determines the highlight color of the {@link ActionChoice} if it is shown on a
     * {@link com.gitgud.engine.view.GridMapRender} as a
     * {@link com.gitgud.engine.view.GridMapRender#addHighLight(Tile, Color, EventHandler)}
     *
     * @return The proper Highlight color for this {@link ActionChoice}
     */
    private @NotNull Color determineHighlightColor()
    {
        Color color = Color.BLUE;
        Action<AaType> action = getAction();
        if (action instanceof AttackAction)
        {
            color = Color.RED;
        }
        else if (action instanceof TileMovementAction)
        {
            color = Color.GREEN;
        }
        return color;
    }
    
    
    @Override
    protected @NotNull ToAction<AaType, Tile> getAction()
    {
        return (ToAction<AaType, Tile>) super.getAction();
    }
}
