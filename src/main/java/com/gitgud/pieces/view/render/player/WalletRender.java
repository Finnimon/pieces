package com.gitgud.pieces.view.render.player;

import com.gitgud.engine.view.Render;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.player.ResourceType;
import com.gitgud.pieces.model.player.Wallet;
import com.gitgud.pieces.view.Constants;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


/**
 * Render for the {@link Wallet}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 10.06.2024
 * @Version: 1.0
 */
public class WalletRender extends VBox implements Render<Wallet>
{
    /**
     * The height of the icons.
     */
    public static final int ICON_HEIGHT = 21;
    
    
    /**
     * The maximum width of the render.
     */
    public static final int MAX_WIDTH = 200;
    
    
    /**
     * Defaults to {@link WalletRender#WalletRender(Wallet)} with the {@code ActiveGameController#getInstance().get()
     * .getPlayer().wallet()}.
     *
     * @Precondition: {@link ActiveGameController#getGameState()}!=
     * {@link com.gitgud.pieces.model.game.GameState#NOT_LOADED}
     * @Postcondition: No Exceptions will be thrown.
     * @see WalletRender#WalletRender(Wallet)
     */
    public WalletRender()
    {
        this(ActiveGameController.getInstance().get().getPlayer().wallet());
    }
    
    
    /**
     * Constructs a new {@link WalletRender} with the given {@link Wallet}
     */
    public WalletRender(@NotNull Wallet wallet)
    {
        render(wallet);
    }
    
    
    @Override
    public void render(@NotNull Wallet model)
    {
        setBackground(Constants.SEMI_TRANSPARENT_BACKGROUND);
        setMaxWidth(MAX_WIDTH);
        setMaxHeight((ICON_HEIGHT + getSpacing()) * ResourceType.values().length);
        setMouseTransparent(true);
        HashMap<ResourceType, SimpleLongProperty> resourceMap = model.resourceMap();
        for (ResourceType resourceType : ResourceType.values())
        {
            render(resourceType, resourceMap.get(resourceType));
        }
    }
    
    
    /**
     * Helper Method for rendering one of the resources in the {@link Wallet}.
     *
     * @param resourceType  The {@link ResourceType}.
     * @param valueProperty The amount of the resource.
     */
    private void render(@NotNull ResourceType resourceType,@NotNull SimpleLongProperty valueProperty)
    {
        ImageView imageView = new ImageView(resourceType.getSprite());
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(ICON_HEIGHT);
        
        StringBinding stringBinding = valueProperty.asString();
        Label label = new Label();
        label.setMinWidth(MAX_WIDTH - getSpacing() - ICON_HEIGHT);
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
        hBox.setBorder(Constants.BLACK_SQUARE_BORDER);
        getChildren().add(hBox);
    }
}
