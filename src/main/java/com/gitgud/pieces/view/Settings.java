package com.gitgud.pieces.view;

import com.gitgud.pieces.control.GameSettings;
import com.gitgud.pieces.control.StageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;


/**
 * Controller for the Settings View.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.07.2024
 * @Version: 1.0
 */
public class Settings
{
    private static final String FXML_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\view\\Settings.fxml";
    
    
    @FXML
    private Pane settingsId;
    
    
    @FXML
    private RadioButton englishId;
    
    
    @FXML
    private RadioButton germanId;
    
    
    @FXML
    private Slider musicVolumeId;
    
    
    public static Pane create()
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
    
    
    public void initialize()
    {
        GameSettings gameSettings = GameSettings.getInstance();
        musicVolumeId.valueProperty().bindBidirectional(gameSettings.musicVolumeProperty());
        boolean english = gameSettings.getLanguage().equals("en");
        englishId.setSelected(english);
        germanId.setSelected(!english);
    }
    
    
    @FXML
    private void englishAction()
    {
        englishId.setSelected(true);
        germanId.setSelected(false);
    }
    
    
    @FXML
    private void germanAction()
    {
        englishId.setSelected(false);
        germanId.setSelected(true);
    }
    
    
    @FXML
    private void saveAction()
    {
        GameSettings gameSettings = GameSettings.getInstance();
        
        gameSettings.setMusicVolume(musicVolumeId.getValue());
        
        if (englishId.isSelected())
        {
            gameSettings.setLanguage("en");
        }
        else if (germanId.isSelected())
        {
            gameSettings.setLanguage("de");
        }
    }
    
    
    @FXML
    private void returnAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().remove(settingsId);
    }
}
