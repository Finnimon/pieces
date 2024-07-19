package com.gitgud.engine.view;

import com.gitgud.engine.control.StageController;
import com.gitgud.engine.control.action.ActionAwaiterModel;
import javafx.scene.Parent;


public interface ActionContextRender<ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends com.gitgud.engine.model.gameobjects.GridMappable> extends HudRender<ModelType>
{
    GridMapRender<GridMappableType> getGridMapRender();
    
    
    @Override
    ActionContextHud<ModelType> getHud();
    
    default void show()
    {
        StageController.getInstance().getStage().getScene().setRoot((Parent) this);
    }
}
