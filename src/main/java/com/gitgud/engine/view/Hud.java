package com.gitgud.engine.view;

import javafx.scene.layout.StackPane;


/**
 * A Heads-Up-Display (HUD) for the game.
 *
 * @param <DataType> The type of data to be rendered.
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public abstract class Hud<DataType> extends StackPane implements UpdatableRender<DataType>
{
    /**
     * The data to be rendered
     */
    private final DataType data;
    
    
    public Hud(DataType data)
    {
        this.data = data;
        // Disable picking on bounds because a HUD fills the screen and is mostly transparent,
        // but should only be selectable where it offers UI elements.
        setPickOnBounds(false);
    }
    
    
    @Override
    public DataType getData()
    {
        return data;
    }
}
