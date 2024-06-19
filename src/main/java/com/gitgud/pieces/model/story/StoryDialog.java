package com.gitgud.pieces.model.story;

import com.gitgud.engine.model.gameObject.Describable;
import com.gitgud.engine.model.gameObject.Named;


public record StoryDialog(String name, String description) implements Describable, Named
{
}
