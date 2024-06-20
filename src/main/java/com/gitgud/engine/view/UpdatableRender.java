package com.gitgud.engine.view;

public interface UpdatableRender<DataType> extends Render<DataType>
{
    public void updateRender();
    public DataType getData();
}
