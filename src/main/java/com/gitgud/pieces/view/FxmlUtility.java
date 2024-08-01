package com.gitgud.pieces.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.FileInputStream;
import java.io.IOException;


/**
 * FxmlUtility class for loading FXML files.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.07.2024
 * @Version: 1.1
 */
public class FxmlUtility
{
    private FxmlUtility()
    {
    }
    
    
    /**
     * Loads an FXML file into memory.
     *
     * @param fxmlPath the path to the FXML file
     * @return the loaded FXML
     */
    public static Node loadFxml(String fxmlPath)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        
        try
        {
            FileInputStream fxmlInputStream = new FileInputStream(fxmlPath);
            
            return fxmlLoader.load(fxmlInputStream);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error loading FXML file: " + fxmlPath, e);
        }
    }
}
