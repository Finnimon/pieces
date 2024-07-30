package com.gitgud.pieces.view.sUnused;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.TerrainType;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.model.player.ResourceType;
import com.gitgud.pieces.model.player.Wallet;
import com.gitgud.pieces.view.IDimentions;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;


/**
 * Offers Methods for rendering a GridMap
 *
 * @author Julius R.
 * @Owner: Julius R.
 * @Since: 01.05.2024
 * @Version: 1.0
 * @deprecated replaced by {@link com.gitgud.engine.view.GridMapRender}
 */
@Deprecated
public class SGridMap implements IDimentions
{
    /**
     * THis Method creates a field of Tiles
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     * @deprecated replaced by {@link com.gitgud.engine.view.GridMapRender}
     */
    protected static void createFieldOfTiles(Group tilesGroup, GridMap gridMap)
    {
        {
            for (int i = 0; i < gridMap.getHeight(); i++)
            {
                for (int j = 0; j < gridMap.getWidth(); j++)
                {
                    Tile tile = (Tile) gridMap.getVertex(i, j);
                    
                    Rectangle rectangle = new Rectangle();
                    double xPosition = tile.getX();
                    double yPosition = tile.getY();
                    rectangle.setX(xPosition * TILE_SPACING);
                    rectangle.setY(yPosition * TILE_SPACING);
                    rectangle.setWidth(TILE_DIMENSIONS);
                    rectangle.setHeight(TILE_DIMENSIONS);
                    
                    if (tile.getTerrain().terrainType() == TerrainType.NON_TRAVERSABLE)
                    {
                        rectangle.setFill(Color.GRAY);
                    }
                    else if ((xPosition + yPosition) % 2 == 1)
                    {
                        rectangle.setFill(Color.SADDLEBROWN);
                    }
                    else
                    {
                        rectangle.setFill(Color.WHEAT);
                    }
                    rectangle.setOnMouseClicked(moveRequestEvent ->
                                                {
                                                    if (moveRequestEvent.getButton() == MouseButton.SECONDARY)
                                                    {
                                                        moveRequest(tile);
                                                    }
                                                });
                    tilesGroup.getChildren().add(rectangle);
                }
            }
        }
    }
    
    
    protected static void moveRequest(Tile tile)
    {
        //todo
    }
    
    
    /**
     * THis Method creates a field of Game Objekts
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    @Deprecated
    protected static <T extends GridMappable> void createFieldOfGameObjects(Group gameObjektGroup, GridMap<T> gridMap)
    {
        for (Tile tile : gridMap.verticeSet())
        {
            T element = gridMap.get(tile);
            if (element == null)
            {
                continue;
            }
            VBox gameObjectContainer = new VBox();
            AnchorPane.setTopAnchor(gameObjectContainer, (double) (tile.getY() * TILE_SPACING));
            AnchorPane.setLeftAnchor(gameObjectContainer, (double) (tile.getX() * TILE_SPACING));
            gameObjectContainer.setPrefSize(TILE_DIMENSIONS, TILE_DIMENSIONS);
            
            Image gameObjektSprite = new Image(element.getSpriteUrl());
            ImageView viewGameObjektSprite = new ImageView(gameObjektSprite);
            
            if (element instanceof FightAgent)
            {
                Rectangle healthBar = new Rectangle();
                healthBar.setHeight(20);
                healthBar.setWidth((float) ((FightAgent) element).getMaxHealth() / ((FightAgent) element).getHealth());
                gameObjectContainer.getChildren().addAll(viewGameObjektSprite, healthBar);
            }
            else
            {
                gameObjectContainer.getChildren().add(viewGameObjektSprite);
            }
            
            gameObjektGroup.getChildren().add(gameObjectContainer);
        }
    }
    
    
    /**
     * THis Method creates the top menu
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    protected static void createTopMenu(HBox menu)
    {
        Player player = ActiveGameController.getInstance().get().getPlayer();
        
        Wallet wallet = player.wallet();
        
        HashMap<ResourceType, SimpleLongProperty> resourceMap = wallet.resourceMap();
        
        for (ResourceType key : resourceMap.keySet())
        {
            Long value = resourceMap.get(key).getValue();
            Label ValueShow = new Label(Long.toString(value));
            HBox box = new HBox();
            
            Image image = new Image(key.getSpriteUrl());
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(TOP_MENU_HEIGHT);
            imageView.setFitWidth(TOP_MENU_HEIGHT);
            box.getChildren().addAll(ValueShow, imageView);
            
            menu.getChildren().add(box);
        }
    }
    
}
