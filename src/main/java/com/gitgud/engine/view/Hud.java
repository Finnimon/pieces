package com.gitgud.engine.view;

import javafx.scene.layout.StackPane;


public abstract class Hud<DataType> extends StackPane implements UpdatableRender<DataType>
{
    
    private final DataType data;
    
    
    public Hud(DataType data)
    {
        this.data = data;
        setPickOnBounds(false);
    }
    
    
    public DataType getModel()
    {
        return data;
    }
}
