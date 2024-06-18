package com.gitgud.engine.control;

import com.gitgud.engine.view.Render;


public abstract class Controller<ModelType>
{
    
    private final ModelType model;
    
    
    private final Render<ModelType> render;
    
    
    public Controller(ModelType model, Render<ModelType> render)
    {
        this.model = model;
        this.render = render;
    }
    
    
    public ModelType getModel()
    {
        return model;
    }
    
    
    public Render<ModelType> getRender()
    {
        return render;
    }
    
}
