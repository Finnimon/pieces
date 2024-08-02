package com.gitgud.pieces.view;

import com.gitgud.pieces.control.StageController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


/**
 * Main menu of the game.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 15.07.2024
 * @Version: 0.2
 */
public class LoadMenu
{
    private static final String FXML_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\view\\LoadMenu.fxml";
    
    
    @FXML
    private StackPane loadMenuId;
    
    
    public static Pane create()
    {
        return (Pane) FxmlUtility.loadFxml(FXML_PATH);
    }
    
    
    @FXML
    private void returnAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().remove(loadMenuId);
    }
}
