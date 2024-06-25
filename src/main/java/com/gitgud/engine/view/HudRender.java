package com.gitgud.engine.view;


public interface HudRender<ModelType> extends UpdatableRender<ModelType>
{
    
    Hud<ModelType> getHud();
}
