package com.gitgud.engine.view;

/**
 * Render with a {@link Hud}
 *
 * @param <ModelType> The ModelType to be rendered.
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public interface HudRender<ModelType> extends UpdatableRender<ModelType>
{
    /**
     * Getter for the Heads-up display.
     *
     * @return The Heads-up display.
     */
    Hud<ModelType> getHud();
}
