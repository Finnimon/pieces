package com.gitgud.engine.view;

import com.gitgud.engine.view.UpdatableRender;
import javafx.scene.Group;


public abstract class Hud<DataType> extends Group implements UpdatableRender<DataType>
{
    private final DataType data;
    
    
    public Hud(DataType data)
    {
        this.data = data;
    }
    
    
    public DataType getData()
    {
        return data;
    }
}
