package com.gitgud.engine.model;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Named;


public record StoryDialog(String name, String description) implements Named, Describable
{
}
