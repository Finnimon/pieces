package com.gitgud.engine.control;

import com.gitgud.engine.view.Render;


public abstract class Controller<ModelType, RenderType extends Render<ModelType>>
{
    
    private final ModelType model;
    
    
    private final RenderType render;
    
    
    public Controller(ModelType model, RenderType render)
    {
        this.model = model;
        this.render = render;
    }
    
    
    public ModelType getModel()
    {
        return model;
    }
    
    
    public RenderType getRender()
    {
        return render;
    }
    
}
