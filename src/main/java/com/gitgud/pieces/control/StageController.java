package com.gitgud.pieces.control;


import com.gitgud.pieces.view.StageStyler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class StageController
{
    
    
    public static final int ROOT_INDEX = 1;
    
    
    private static StageController instance = null;
    
    
    private final Stage stage;
    
    
    private StageController(Stage stage)
    {
        this.stage = stage;
    }
    
    
    public static StageController getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException("Not yet initialized");
        }
        return instance;
    }
    
    
    public static void initialize(Stage stage)
    {
        if (instance != null)
        {
            throw new IllegalStateException("Already initialized");
        }
        StageStyler.style(stage);
        instance = new StageController(stage);
    }
    
    
    public void setRoot(Node node)
    {
        StackPane stackPane= getInnerStackPane();
        stackPane.getChildren().set(0, node);
    }
    
    
    private StackPane getInnerStackPane()
    {
        return (StackPane) ((Pane) stage.getScene().getRoot()).getChildren().get(ROOT_INDEX);
    }
    
    
    public Parent getRoot()
    {
        return (Parent) getInnerStackPane().getChildren().getFirst();
    }
    
    
    public void show()
    {
        stage.show();
    }
}
