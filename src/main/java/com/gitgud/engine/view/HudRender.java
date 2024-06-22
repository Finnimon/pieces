package com.gitgud.engine.view;

import com.gitgud.pieces.view.render.Hud;


public interface HudRender<ModelType> extends UpdatableRender<ModelType>
{
    
    Hud<ModelType> getHud();
}
