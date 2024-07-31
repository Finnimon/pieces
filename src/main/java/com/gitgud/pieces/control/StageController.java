package com.gitgud.pieces.control;


import com.gitgud.pieces.view.StageStyler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Singleton class that controls the stage and asserts, that the MenuBar is always at the top.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see StageStyler
 * @since 15.07.2024
 */
public class StageController
{
    /**
     * Index of the editable Scene Root within the children of the root of the JavaFX {@link Scene} {@code scene
     * .getRoot().getChildren()}
     */
    public static final int ROOT_INDEX = 1;
    
    
    /**
     * Singleton Instance.
     */
    private static StageController instance = null;
    
    
    /**
     * Stage to control.
     */
    private final Stage stage;
    
    
    /**
     * Private constructor.
     *
     * @param stage Stage to control.
     * @throws IllegalStateException If the Singleton has already been initialized.
     * @Precondition: The Singleton has not been initialized.
     * @Postcondition: No Exceptions will be thrown.
     */
    private StageController(Stage stage)
    {
        if (instance != null)
        {
            throw new IllegalStateException("Already initialized");
        }
        this.stage = stage;
    }
    
    
    /**
     * Getter for the singleton instance.
     *
     * @return Singleton instance.
     */
    public static StageController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException("Not yet initialized");
        }
        return instance;
    }
    
    
    /**
     * Initializes the singleton instance and styles the {@code stage}.
     *
     * @param stage The stage to initialize the Singleton with and style.
     * @throws IllegalStateException If the Singleton has already been initialized.
     * @Precondition: The Singleton has not been initialized.
     * @Postcondition: No Exceptions will be thrown.
     */
    public static void initialize(Stage stage)
    {
        if (instance != null)
        {
            throw new IllegalStateException("Already initialized");
        }
        StageStyler.style(stage);
        instance = new StageController(stage);
    }
    
    
    /**
     * Resets the singleton.
     */
    public static void reset()
    {
        instance = null;
    }
    
    
    /**
     * Gets the current editable root of the Scene.
     *
     * @return The current editable root of the Scene.
     */
    public Parent getRoot()
    {
        return (Parent) getInnerStackPane().getChildren().getFirst();
    }
    
    
    private StackPane getInnerStackPane()
    {
        return (StackPane) ((Pane) stage.getScene().getRoot()).getChildren().get(ROOT_INDEX);
    }
    
    
    /**
     * Sets the current editable root of the Scene.
     *
     * @param node The new editable root of the Scene.
     */
    public void setRoot(Parent node)
    {
        StackPane stackPane = getInnerStackPane();
        stackPane.getChildren().set(0, node);
    }
    
    
    /**
     * <p>Shows the stage.
     *
     * @see Stage#show()
     */
    public void show()
    {
        stage.show();
    }
}
