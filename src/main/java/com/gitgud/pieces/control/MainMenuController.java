package com.gitgud.pieces.control;

import com.gitgud.engine.control.Startable;
import com.gitgud.pieces.model.game.GameState;
import com.gitgud.pieces.view.MainMenu;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


/**
 * Startable for {@link com.gitgud.pieces.view.MainMenu}
 */
public class MainMenuController implements Startable
{
    public static final int MAIN_MENU_INDEX_WHEN_LOADED = 1;
    
    
    private final StackPane mainMenu;
    
    
    public MainMenuController()
    {
        this.mainMenu = MainMenu.create();
    }
    
    
    @Override
    public void start()
    {
        if (ActiveGameController.getGameState() == GameState.NOT_LOADED)
        {
            StageController.getInstance().setRoot(mainMenu);
            return;
        }
        ((Pane) StageController.getInstance().getRoot()).getChildren().add(MAIN_MENU_INDEX_WHEN_LOADED, mainMenu);
    }
}
