package com.gitgud.pieces.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;


public class SBasisCamp
{
    
    private static final String[] recruteUnits = { "Einheit 1", "Einheit 2", "Einheit 3", "Einheit 4" };
    
    
    public static Scene createBasiscampScene(Stage stage)
    {
        Label selected = new Label();
        Label description = new Label("Hier werden Einheiten rekrutiert");
        description.setFont(Font.font(null, FontWeight.BOLD, 20));
        
        ArrayList<String> selectedUnits = new ArrayList<>();
        VBox layout = new VBox(50);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        HBox units = new HBox(60);
        units.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < recruteUnits.length; i++)
        {
            VBox unit = new VBox(60);
            unit.setAlignment(Pos.CENTER);
            Label name = new Label(recruteUnits[i]);
            name.setFont(Font.font(null, FontWeight.MEDIUM, 20));
            Button select = new Button("Rekrutiere");
            unit.getChildren().addAll(name, select);
            units.getChildren().add(unit);
            int finalI = i;
            select.setOnAction(e ->
                               {
                                   selectedUnits.add(recruteUnits[finalI]);
                                   selected.setText(selectedUnits.toString().replace("[", "").replace("]", ""));
                               });
        }
        
        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> SCity.createCityScene(stage));
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        layout.getChildren().addAll(description, selected, units, continueButton);
        
        
        stage.setScene(scene);
        return scene;
    }
    
}