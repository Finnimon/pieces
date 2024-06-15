package com.gitgud.view;

import com.gitgud.control.PlayerController;
import com.gitgud.model.gameObjects.GridMappable;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMap;
import com.gitgud.model.map.TerrainType;
import com.gitgud.model.map.Tile;
import com.gitgud.model.player.Player;
import com.gitgud.model.player.ResourceType;
import com.gitgud.model.player.Wallet;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class SGridMap implements IDimentions {
    /**
     * THis Method creates a field of Tiles
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    protected static void createFieldOfTiles(Group tilesGroup,  GridMap gridMap)
    {
        {
            for (int i = 0; i < gridMap.getHeight(); i++)
            {
                for (int j = 0; j < gridMap.getWidth(); j++)
                {
                    Tile tile = gridMap.getTile(i, j);

                    Rectangle rectangle = new Rectangle();
                    int xPosition = tile.xPosition();
                    int yPosition = tile.yPosition();
                    rectangle.setX(xPosition * TILE_SPACING);
                    rectangle.setY(yPosition * TILE_SPACING);
                    rectangle.setWidth(TILE_DIMENSIONS);
                    rectangle.setHeight(TILE_DIMENSIONS);

                    if (tile.terrain().terrainType() == TerrainType.NON_TRAVERSABLE)
                    {
                        rectangle.setFill(Color.GRAY);
                    } else if ((xPosition + yPosition) % 2 == 1)
                    {
                        rectangle.setFill(Color.SADDLEBROWN);
                    } else
                    {
                        rectangle.setFill(Color.WHEAT);
                    }
                    rectangle.setOnMouseClicked(moveRequestEvent -> {
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
    /**
     * THis Method creates a field of Game Objekts
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    protected static <T extends GridMappable> void createFieldOfGameObjects(Group gameObjektGroup, GridMap<T> gridMap)
    {
        AnchorPane anchorPane = new AnchorPane();
        for (Tile tile : gridMap.keySet())
        {
            T element = gridMap.get(tile);
            if (element == null)
            {
                continue;
            }
            VBox gameObjectContainer = new VBox();
            anchorPane.setTopAnchor(gameObjectContainer, (double) (tile.yPosition() * TILE_SPACING));
            anchorPane.setLeftAnchor(gameObjectContainer, (double) (tile.xPosition() * TILE_SPACING));
            gameObjectContainer.setPrefSize(TILE_DIMENSIONS, TILE_DIMENSIONS);
            gameObjectContainer.addEventHandler(MouseEvent.MOUSE_CLICKED, Event::consume);

            gameObjectContainer.setOnMouseClicked(moveRequestEvent -> {
                if (moveRequestEvent.getButton() == MouseButton.SECONDARY)
                {
                    interactionRequest(element);
                }
            });


            Image gameObjektSprite = new Image(element.getSpriteUrl());
            ImageView viewGameObjektSprite = new ImageView(gameObjektSprite);

            if (element instanceof FightAgent)
            {
                Rectangle healthBar = new Rectangle();
                healthBar.setHeight(20);
                healthBar.setWidth((float)((FightAgent) element).getMaxHealth()/((FightAgent) element).getHealth());
                gameObjectContainer.getChildren().addAll(viewGameObjektSprite, healthBar);
            }
            else
            {
                gameObjectContainer.getChildren().add(viewGameObjektSprite);
            }

            gameObjektGroup.getChildren().add(gameObjectContainer);
        }
    }

    protected static void createTopMenu(HBox menu)
    {
        Player player = PlayerController.getInstance().getPlayer();

        Wallet wallet = player.wallet();

        HashMap<ResourceType, Long> resourceMap = wallet.resourceMap();

        for (ResourceType key : resourceMap.keySet())
        {
            long value = resourceMap.get(key);
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

    private static <T extends GridMappable> void interactionRequest(T element)
    {
        //todo
    }
    protected static void moveRequest(Tile tile)
    {
        //todo
    }

}