package com.gitgud.pieces.view;


import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.control.StageController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


/**
 * Save as of the game.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 15.07.2024
 * @Version: 0.2
 */
public class SaveAs
{
    private static final String FXML_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\view\\SaveAs.fxml";
    
    
    @FXML
    private Pane saveAsId;
    
    
    @FXML
    private TextField saveAsNameId;
    
    
    public static Pane create()
    {
        return (Pane) FxmlUtility.loadFxml(FXML_PATH);
    }
    
    
    @FXML
    private void saveAsAction()
    {
        Game.Saver.saveAs(saveAsNameId.getText());
    }
    
    
    @FXML
    private void returnAction()
    {
        removeMenuPane(saveAsId);
    }
    
    
    /**
     * Removes a MenuPane from the root of the Scene.
     *
     * @param menuPane The MenuPane to remove.
     */
    public static void removeMenuPane(Pane menuPane)
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().remove(menuPane);
    }
    
}
