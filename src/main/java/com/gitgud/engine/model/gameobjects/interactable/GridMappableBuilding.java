package com.gitgud.engine.model.gameobjects.interactable;

import com.gitgud.engine.control.ActionAwaitingController;
import com.gitgud.engine.model.gameobjects.GameObject;
import org.jetbrains.annotations.NotNull;


/**
 * Interactables that remain on the Map and can be interacted with. As compared to {@link Collectible} which will be
 * removed after interaction.
 *
 * @param <AAType> The type of the {@link ActionAwaitingController} in which this can be interacted with.
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 16.04.2022
 */
public abstract class GridMappableBuilding<AAType extends ActionAwaitingController> extends GameObject
        implements Interactable<AAType>
{
    /**
     * Defaults to {@link GameObject#GameObject(String, String, String)}
     *
     * @see GameObject#GameObject(String, String, String)
     */
    public GridMappableBuilding(@NotNull String name, @NotNull String description, @NotNull String spriteUrl)
    {
        super(name, description, spriteUrl);
    }
}
