package com.gitgud.engine.model;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Named;


/**
 * Can be used to describe a Story Dialog in the Game.
 * @param name The Title of the Dialog.
 * @param description The actual text of the Dialog.
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 22.04.2024
 */
public record StoryDialog(String name, String description) implements Named, Describable
{
}
