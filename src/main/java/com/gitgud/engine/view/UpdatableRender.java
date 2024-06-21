package com.gitgud.engine.view;

public interface UpdatableRender<DataType> extends Render<DataType>
{
    void updateRender();
    
    
    DataType getData();
}
