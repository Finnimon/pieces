package com.gitgud.pieces.view;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.model.game.GameState;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


/**
 * Main menu of the game.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 15.07.2024
 * @Version: 0.2
 */
public class MainMenu
{
    private static final String FXML_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\view\\MainMenu.fxml";
    
    
    public static final EventHandler<KeyEvent> OPEN_MAIN_MENU_ON_ESCAPE = e ->
    {
        if (e.getCode() != KeyCode.ESCAPE)
        {
            return;
        }
        e.consume();
        showMenuPane(create());
    };
    
    
    private static final int ADDITIONAL_PANE_INDEX = 1;
    
    
    @FXML
    private StackPane mainMenuId;
    
    
    public static Pane create()
    {
        return (Pane) FxmlUtility.loadFxml(FXML_PATH);
    }
    
    
    @FXML
    private void exitAction()
    {
        Game.Flow.end();
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
        showMenuPane(NewGame.create());
    }
    
    
    protected static void showMenuPane(Pane e)
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().add(e);
    }
    
    
    @FXML
    private void loadAction()
    {
        showMenuPane(LoadMenu.create());
    }
    
    
    @FXML
    private void settingsAction()
    {
        showMenuPane(Settings.create());
    }
    
}
