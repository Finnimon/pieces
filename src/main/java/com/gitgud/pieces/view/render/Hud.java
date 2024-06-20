package com.gitgud.pieces.view.render;

import com.gitgud.engine.view.Render;
import com.gitgud.engine.view.UpdatableRender;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.Collection;


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
