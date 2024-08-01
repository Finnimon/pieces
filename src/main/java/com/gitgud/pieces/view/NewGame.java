package com.gitgud.pieces.view;

import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.model.player.Difficulty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


/**
 * New game menu of the game.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 15.07.2024
 * @Version: 0.2
 */
public class NewGame
{
    private static final String FXML_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\view\\NewGame.fxml";
    
    
    @FXML
    public TextField nameId;
    
    
    @FXML
    private Pane newGameId;
    
    
    @FXML
    private Slider difficultyId;
    
    
    public static Pane create()
    {
        return (Pane) FxmlUtility.loadFxml(FXML_PATH);
    }
    
    
    @FXML
    private void newGameAction()
    {
        Game.New.start(nameId.textProperty().get(),
                       Difficulty.values()[(int) Math.round(difficultyId.valueProperty().get())]);
    }
    
    
    @FXML
    private void returnAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().remove(newGameId);
    }
}
