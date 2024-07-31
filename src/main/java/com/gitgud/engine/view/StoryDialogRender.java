package com.gitgud.engine.view;


import com.gitgud.engine.model.StoryDialog;
import com.gitgud.engine.model.gameobjects.Sprite;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;


/**
 * Render for {@link StoryDialog}. Allows for a {@link StoryDialog} to be rendered and stylized.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class StoryDialogRender extends StackPane implements Render<StoryDialog>
{
    /**
     * The title Label.
     */
    private final Label title;
    
    
    /**
     * The content Label.
     */
    private final Label content;
    
    
    /**
     * Default Constructor that creates a new StoryDialogRender.
     * Renders the given StoryDialog.
     *
     * @param storyDialog The StoryDialog to be rendered.
     */
    public StoryDialogRender(@NotNull StoryDialog storyDialog)
    {
        super();
        title = new Label();
        content = new Label();
        getChildren().addAll(title, content);
        render(storyDialog);
    }
    
    
    @Override
    public void render(@NotNull StoryDialog model)
    {
        title.textProperty().set(model.name());
        content.textProperty().set(model.description());
        
        setAlignment(title, Pos.TOP_LEFT);
        setAlignment(content, Pos.TOP_RIGHT);
        content.translateYProperty().bind(title.heightProperty());
    }
    
    
    /**
     * Adds a non-repeating background to the dialog.
     *
     * @param sprite The sprite for the background.
     */
    public void addBackGround(Sprite sprite)
    {
        BackgroundImage backgroundImage = new BackgroundImage(sprite.getSprite(),
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundPosition.CENTER,
                                                              BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }
    
    
    /**
     * Sets the css Style for both the title and content label.
     *
     * @param style The css style.
     * @see Label#setStyle(String)
     */
    public void setTextStyle(String style)
    {
        setTitleStyle(style);
        setContentStyle(style);
    }
    
    
    /**
     * Sets the css Style for the title label.
     *
     * @param style The css style.
     * @see Label#setStyle(String)
     */
    public void setTitleStyle(String style)
    {
        title.setStyle(style);
    }
    
    
    /**
     * Sets the css Style for the content label.
     *
     * @param style The css style.
     * @see Label#setStyle(String)
     */
    public void setContentStyle(String style)
    {
        content.setStyle(style);
    }
    
}
