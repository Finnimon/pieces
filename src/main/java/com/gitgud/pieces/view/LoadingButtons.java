package com.gitgud.pieces.view;

import com.gitgud.pieces.control.Game;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


/**
 * Main menu of the game.
 *
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 15.07.2024
 * @Version: 0.2
 */
public class LoadingButtons extends VBox
{
    public LoadingButtons()
    {
        for (String string : Game.Loader.getLoadableSaveFileNames())
        {
            Button button = new Button(string);
            button.setPrefSize(500, 200);
            button.setOnAction(e -> Game.Loader.load(string));
            getChildren().add(button);
        }
    }
}
