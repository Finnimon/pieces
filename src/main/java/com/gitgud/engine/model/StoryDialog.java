package com.gitgud.engine.model;

import com.gitgud.engine.model.gameObject.Describable;
import com.gitgud.engine.model.gameObject.Named;


public record StoryDialog(String name, String description) implements Named, Describable
{
}
