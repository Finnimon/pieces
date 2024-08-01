package com.gitgud.engine.control.actionChoice;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.RootActionChoiceRender;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * RootChoice that contains multiple unspecified ActionChoices
 *
 * @param <AaType> The type of the {@link ActionAwaitingController} this Action can be applied to.
 * @param <MType>  The type of the {@link ActionAwaiterModel} this Action can be applied to.
 * @param <GmType> The type of the {@link GridMappable} this Action can be applied to.
 * @param <RType>  The type of the {@link ActionContextRender} this Action can be applied to.
 * @author Finn L.
 * @version 1.1
 * @Owner: Finn L.
 * @see RootChoice
 * @since 28.06.2024
 */
public class RootActionChoice<AaType extends ActionAwaitingController<MType, GmType, RType>,
        MType extends ActionAwaiterModel<GmType>, GmType extends GridMappable,
        RType extends ActionContextRender<MType, GmType>>
        extends ActionChoice<AaType, MType, GmType, RType>
        implements RootChoice<ActionChoice<AaType, MType, GmType, RType>>
{
    /**
     * The child Choices of this RootChoice
     */
    private final List<ActionChoice<AaType, MType, GmType, RType>> choices;
    
    
    /**
     * Creates a new RootToActionChoice and sets all attributes.
     *
     * @param name        The name of this ActionChoice
     * @param description The description of this ActionChoice
     * @param awaiter     The targeted {@link ActionAwaitingController}.
     * @param choices     The child choices for this RootChoice.
     */
    public RootActionChoice(@NotNull String name, @NotNull String description, @NotNull AaType awaiter,
                            @NotNull List<ActionChoice<AaType, MType, GmType, RType>> choices)
    {
        super(name, description, awaiter);
        this.choices = choices;
    }
    
    
    @Override
    public @NotNull List<ActionChoice<AaType, MType, GmType, RType>> getChildren()
    {
        return choices;
    }
    
    
    @Override
    public void show(@NotNull AaType actionAwaiter)
    {
        RootActionChoiceRender render = new RootActionChoiceRender(this);
        actionAwaiter.getRender().getHud().addChoice(render);
    }
}
