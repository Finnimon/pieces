package com.gitgud.engine.view;

import com.gitgud.engine.view.UpdatableRender;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public abstract class Hud<DataType> extends Pane implements UpdatableRender<DataType>
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
