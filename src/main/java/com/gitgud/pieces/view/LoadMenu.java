package com.gitgud.pieces.view;

import com.gitgud.pieces.control.StageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.IOException;


public class LoadMenu
{
    private static final String FXML_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\view\\LoadMenu.fxml";
    
    
    @FXML
    private StackPane loadMenuId;
    
    
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
    private void returnAction()
    {
        ((Pane) StageController.getInstance().getRoot()).getChildren().remove(loadMenuId);
    }
}
