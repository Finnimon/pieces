package com.gitgud.pieces.view.render.player;

import com.gitgud.engine.view.Render;
import com.gitgud.engine.view.infopane.InfoPane;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.player.ResourceType;
import com.gitgud.pieces.model.player.Wallet;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;


public class WalletRender extends VBox implements Render<Wallet>
{
    
    
    public static final int ICON_HEIGHT = 21;
    
    
    public static final int MAX_WIDTH = 200;
    
    
    public WalletRender(Wallet wallet)
    {
        render(wallet);
    }
    
    
    public WalletRender()
    {
        this(ActiveGameController.getInstance().get().getPlayer().wallet());
    }
    
    
    @Override
    public void render(Wallet data)
    {
        setMaxWidth(MAX_WIDTH);
        setMaxHeight((ICON_HEIGHT +getSpacing())*ResourceType.values().length);
        setMouseTransparent(true);
        HashMap<ResourceType, SimpleLongProperty> resourceMap = data.resourceMap();
        for (ResourceType resourceType : ResourceType.values())
        {
            render(resourceType, resourceMap.get(resourceType));
        }
    }
    
    
    private void render(ResourceType resourceType, SimpleLongProperty valueProperty)
    {
        ImageView imageView = new ImageView(resourceType.getSprite());
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(ICON_HEIGHT);
        
        StringBinding stringBinding = valueProperty.asString();
        Label label = new Label();
        label.setMinWidth(MAX_WIDTH-getSpacing() - ICON_HEIGHT);
        label.setPrefHeight(ICON_HEIGHT);
        label.textProperty().bind(stringBinding);
        label.contentDisplayProperty().set(ContentDisplay.RIGHT);
        label.setTextAlignment(TextAlignment.RIGHT);
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setPadding(new Insets(0, 20, 0, 0));
        
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(imageView, label);
        hBox.setMaxHeight(ICON_HEIGHT);
        hBox.maxWidthProperty().setValue(MAX_WIDTH);

        getChildren().add(hBox);
    }
}
