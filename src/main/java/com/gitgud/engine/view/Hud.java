package com.gitgud.engine.view;

import javafx.scene.layout.BorderPane;


public abstract class Hud<DataType> extends BorderPane implements UpdatableRender<DataType>
{
    
    private final DataType data;
    
    
    public Hud(DataType data)
    {
        this.data = data;
        setPickOnBounds(false);
    }
    
    
    public DataType getData()
    {
        return data;
    }
}
