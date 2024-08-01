package com.gitgud.pieces.view;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.control.MainMenuController;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.model.game.GameState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;


/**
 * Main menu of the game.
 */
public class MainMenu
{
    private static final String FXML_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\view\\MainMenu.fxml";
    
    private static final int ADDITIONAL_PANE_INDEX = 1;
    
    
    @FXML
    private StackPane mainMenuId;
    
    
    public static StackPane create()
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        
        try
        {
            FileInputStream fxmlInputStream = new FileInputStream(FXML_PATH);
            
            return fxmlLoader.load(fxmlInputStream);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error loading FXML file: " + FXML_PATH, e);
        }
    }
    
    
    @FXML
    private void exitAction()
    {
        System.exit(0);
    }
    
    
    @FXML
    private void continueAction()
    {
        if (ActiveGameController.getGameState() == GameState.NOT_LOADED)
        {
            return;
        }
        
        ((Pane) StageController.getInstance().getRoot()).getChildren().remove(mainMenuId);
    }
    
    
    @FXML
    private void newGameAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().add(NewGame.create());
    }
    
    
    @FXML
    private void loadAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().add(LoadMenu.create());
    }
    
    
    @FXML
    private void settingsAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().add(Settings.create());
    }
    
}