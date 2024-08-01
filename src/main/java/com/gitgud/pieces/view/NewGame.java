package com.gitgud.pieces.view;

import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.control.StageController;
import com.gitgud.pieces.model.player.Difficulty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;


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
    private void newGameAction()
    {
        Game.New.start(nameId.textProperty().get(), Difficulty.values()[(int) Math.round(difficultyId.valueProperty().get())]);
    }
    @FXML
    private void returnAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().remove(newGameId);
    }
}