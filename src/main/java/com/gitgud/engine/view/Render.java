package com.gitgud.engine.view;

public interface Render<T>
{
    void reRender();
    
    
    void initialRender();
    
    
    void removeFromStage();
}
