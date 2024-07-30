package com.gitgud.engine.view;


import com.gitgud.engine.model.StoryDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class StoryDialogRender extends StackPane implements Render<StoryDialog>
{
    public StoryDialogRender(StoryDialog storyDialog)
    {
        super();
        render(storyDialog);
    }
    
    
    @Override
    public void render(StoryDialog model)
    {
        getChildren().add(new Label(model.name()));
    }
}
