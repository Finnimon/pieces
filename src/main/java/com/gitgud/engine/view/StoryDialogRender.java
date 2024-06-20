package com.gitgud.engine.view;


import com.gitgud.engine.model.StoryDialog;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class StoryDialogRender extends StackPane implements Render<StoryDialog>
{
    public StoryDialogRender(StoryDialog storyDialog)
    {
        super();
        render(storyDialog);
    }
    
    
    @Override
    public void render(StoryDialog data)
    {
        getChildren().add(new Label(data.name()));
    }
}
