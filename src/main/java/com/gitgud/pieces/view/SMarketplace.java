package com.gitgud.pieces.view;

import com.gitgud.pieces.model.player.ResourceType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SMarketplace
{
    public static Scene createMarketplaceScene(Stage stage)
    {
        HBox hbox = new HBox(50);
        VBox text = new VBox(50);
        text.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        Label description = new Label("Hier können Ressourcen gekauft bzw. getauscht werden");
        Scene scene = new Scene(text);
        
        for (ResourceType resource : ResourceType.values())
        {
            VBox vbox = new VBox(20);
            vbox.setAlignment(Pos.CENTER);
            ComboBox anzahlToTrade = new ComboBox();
            ComboBox resourceToTrade = new ComboBox();
            ComboBox anzahlToBuy = new ComboBox();
            anzahlToBuy.getItems().addAll(1, 2, 3, 4, 5);
            anzahlToBuy.setValue(0);
            resourceToTrade.getItems().addAll(ResourceType.values());
            resourceToTrade.setValue(resource);
            anzahlToTrade.getItems().addAll(1, 2, 3, 4, 5);
            anzahlToTrade.setValue(0);
            Button trade = new Button("Trade");
            Button buy = new Button("Buy");
            buy.setOnAction(e ->
                            {
                                System.out.println("Buying " + anzahlToBuy.getValue() + " " + resource.name());
                            });
            trade.setOnAction(e ->
                              {
                                  System.out.println(
                                          "Trading " + anzahlToTrade.getValue() + " " + resource.name() + " for " + resourceToTrade.getValue());
                              });
            vbox.getChildren().addAll(new Label("Trade " + resource.name() + " for:"), resourceToTrade,
                                      new Label("How many?"), anzahlToTrade, trade, new Label("or buy"), anzahlToBuy,
                                      buy);
            hbox.getChildren().add(vbox);
        }
        Button button = new Button("Fortfahren");
        button.setOnAction(e ->
                           {
                               SCity.createCityScene(stage);
                           });
        text.getChildren().addAll(description, hbox, button);
        text.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null)));
        stage.setScene(scene);
        return scene;
    }
}
