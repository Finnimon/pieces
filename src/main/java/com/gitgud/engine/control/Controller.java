package com.gitgud.engine.control;

import com.gitgud.engine.view.Render;
import org.jetbrains.annotations.NotNull;


/**
 * A Simple Controller superclass to be used in this framework.
 *
 * @param <ModelType>  The type of Model used by this Controller.
 * @param <RenderType> The type of Render used by this Controller.
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.05.2024
 * @Version: 1.0
 */
public abstract class Controller<ModelType, RenderType extends Render<ModelType>>
{
    /**
     * This Controller's Model
     */
    private final ModelType model;
    
    
    /**
     * This Controller's Render
     */
    private final RenderType render;
    
    
    public Controller(@NotNull ModelType model, @NotNull RenderType render)
    {
        this.model = model;
        this.render = render;
    }
    
    
    /**
     * Getter for the Controller's Model.
     *
     * @return the Controller's Model {@link #model}
     */
    public ModelType getModel()
    {
        return model;
    }
    
    
    /**
     * Getter for the Controller's Render.
     *
     * @return the Controller's Render {@link #render}
     */
    public RenderType getRender()
    {
        return render;
    }
    
}
