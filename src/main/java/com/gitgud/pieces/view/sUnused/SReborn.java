package com.gitgud.pieces.view.sUnused;

import com.gitgud.engine.model.gameobjects.Sprite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.MalformedURLException;


/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 18.06.2024
 */
public class SReborn
{
    
    private static final String[] content = { "Accitani", "Terros", "Lacces" };
    
    
    private static final String[] attributes = { "Talent", "Skill", "Bonuses" };
    
    
    public static Scene createRebornScene(Stage stage)
    {
        HBox hbox = new HBox();
        Scene rebornScene = new Scene(hbox);
        
        VBox playerBox = new VBox(50);
        playerBox.setAlignment(Pos.TOP_CENTER);
        playerBox.getChildren().add(new Label("Player"));
        playerBox.setMinWidth(stage.getWidth() / (content.length + 1));
        try
        {
            playerBox.setBackground(new Background(new BackgroundImage(new Image(Sprite.urlFromFilePath(
                    "src/main/resources/com/gitgud/playerSpritee/PlayerSprite.png")),
                                                                       BackgroundRepeat.NO_REPEAT,
                                                                       BackgroundRepeat.NO_REPEAT,
                                                                       BackgroundPosition.CENTER,
                                                                       new BackgroundSize(BackgroundSize.AUTO,
                                                                                          BackgroundSize.AUTO,
                                                                                          false,
                                                                                          false,
                                                                                          true,
                                                                                          false))));
        }
        catch (MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
        hbox.getChildren().add(playerBox);
        
        Color[] colors = { Color.LIGHTGREY, Color.BEIGE };
        
        for (int i = 0; i < content.length; i++)
        {
            VBox vbox = new VBox(50);
            vbox.setBackground(new Background(new BackgroundFill(colors[i % 2], new CornerRadii(10), new Insets(25))));
            
            Button button = new Button("Choose");
            button.setOnAction(e ->
                               {
                                   SUnits.createUnitsScene(stage);
                               });
            
            vbox.getChildren().add(new Label(content[i]));
            
            for (int j = 0; j < content.length; j++)
            {
                Label attributesLabel = new Label(attributes[j]);
                vbox.getChildren().add(attributesLabel);
                
                
            }
            
            vbox.setAlignment(Pos.TOP_CENTER);
            vbox.getChildren().add(button);
            vbox.setMinWidth(stage.getWidth() / (content.length + 1));
            hbox.getChildren().add(vbox);
        }
        
        hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        stage.setScene(rebornScene);
        return rebornScene;
    }
}
