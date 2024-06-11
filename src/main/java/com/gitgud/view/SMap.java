package com.gitgud.view;

import com.gitgud.control.PlayerController;
import com.gitgud.model.gameObjects.GridMappable;
import com.gitgud.model.player.Player;
import com.gitgud.model.player.ResourceType;
import com.gitgud.model.player.Wallet;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;


public class SMap {

    private static final int STAGE_WITH = 1000;
    private static final int MAIN_MAP_HEIGHT = 600;
    private static final int TOP_MENU_HEIGHT = 100;
    private static final int BOTTOM_MENU_HEIGHT = 300;
    private static final int TILE_DIMENSIONS = 100;
    private static final int TILE_SPACING = 110;
    private static final int BC_MENU_WIDTH = 500;
    private static final int BS_MENU_WIDTH = 250;
    private static final int NUMBER_OF_UNITS =  5;
    private static final String MOVE_TRANSITION_INFO = "Press 1 to skip the turn and get more steps on the next turn,\n" + "Press 2 to skip the turn and regenerate a little mana,\n" + "Press 3 to skip turn and heal your units,";

    /**
     * This Method creates the Map-scene including: the Feld of Tiles, the Move transition methods, The Inventory,
     * Methods for displaying the game objects
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    public static Scene createMapScene(Stage stage, GridMap gridMap)
    {

        Group root = new Group();
        Scene MapScene = new Scene(root);
        stage.setScene(MapScene);

        BorderPane mapMenue = new BorderPane();
        ScrollPane mainMap = new ScrollPane();
        mainMap.setPrefSize(STAGE_WITH, MAIN_MAP_HEIGHT);

        Group tilesGroup = new Group();
        createFieldOfTiles(tilesGroup, gridMap);
        mainMap.setContent(tilesGroup);

        Group gameObjektGroup = new Group();
        createFieldOfGameObjects(gameObjektGroup, gridMap);
        mainMap.setContent(gameObjektGroup);

        Group PlayerAgentRender = new Group();
        // createFieldOfGameObjects(PlayerAgentRender);
        mainMap.setContent(gameObjektGroup);

        HBox topMenue = new HBox();
        topMenue.setMinHeight(TOP_MENU_HEIGHT);
        createTopMenu(topMenue);

        HBox bottomMenue = new HBox();
        bottomMenue.setMinHeight(BOTTOM_MENU_HEIGHT);
        createBottomMenu(bottomMenue);

        mapMenue.setTop(topMenue);
        mapMenue.setBottom(bottomMenue);
        mapMenue.setCenter(mainMap);

        root.getChildren().add(mapMenue);
        return MapScene;
    }


    /**
     * THis Method creates a field of Tiles
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    public static void createFieldOfTiles(Group tilesGroup, GridMap gridMap)
    {
        {
            for (int i = 0; i < gridMap.getHeight(); i++)
            {
                for (int j = 0; j < gridMap.getWidth(); j++)
                {
                    Tile tile = gridMap.getNode(i, j);

                    Rectangle rectangle = new Rectangle();
                    int xPosition = tile.xPosition();
                    int yPosition = tile.yPosition();
                    rectangle.setX(xPosition * TILE_SPACING);
                    rectangle.setY(yPosition * TILE_SPACING);
                    rectangle.setWidth(TILE_DIMENSIONS);
                    rectangle.setHeight(TILE_DIMENSIONS);

                    // if (tile.terrain().terrainType() == TerrainType.MOUNTAIN)
                    {
                        rectangle.setFill(Color.GRAY);
                    } // else if ((xPosition + yPosition) % 2 == 1)
                    {
                        rectangle.setFill(Color.SADDLEBROWN);
                    } // else
                    {
                        rectangle.setFill(Color.WHEAT);
                    }
                    rectangle.setOnMouseClicked(moveRequestEvent -> {
                        if (moveRequestEvent.getButton() == MouseButton.SECONDARY)
                        {
                            moveRequest(rectangle);
                        }
                    });
                    tilesGroup.getChildren().add(rectangle);
                }
            }
        }
    }

    public static <T extends GridMappable> void createFieldOfGameObjects(Group gameObjektGroup, GridMap<T> gridMap)
    {
        AnchorPane anchorPane = new AnchorPane();
        for (int i = 0; i < gridMap.getHeight(); i++)
        {
            for (int j = 0; j < gridMap.getWidth(); j++)
            {
                Tile tile = gridMap.getNode(i, j);
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

                // Image gameObjektSprite = new Image(); //TODO: Need Game Objekt URL
                //ImageView viewGameObjektSprite = new ImageView(gameObjektSprite);
                Rectangle healthBar = new Rectangle();

                // gameObjectContainer.getChildren().addAll(viewGameObjektSprite, healthBar);
                gameObjektGroup.getChildren().add(gameObjectContainer);
            }
        }
    }


    public static void moveRequest(Rectangle tile)
    {
        System.out.println(tile);
    }

    private static void createTopMenu(HBox menu)
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

    private static void createBottomMenu(HBox menu)
    {
        VBox bottomCentreMenu = new VBox();
        bottomCentreMenu.setPrefSize(BC_MENU_WIDTH, BOTTOM_MENU_HEIGHT);
        HBox BottomLeftMenu = new HBox();
        BottomLeftMenu.setPrefSize(BS_MENU_WIDTH, BOTTOM_MENU_HEIGHT);
        TextField bottomRightMenu = new TextField(MOVE_TRANSITION_INFO);
        bottomRightMenu.setPrefSize(BS_MENU_WIDTH, BOTTOM_MENU_HEIGHT);

        createBottomLeftMenu(bottomCentreMenu);
        createBottomCentreMenu(BottomLeftMenu);
        menu.getChildren().addAll(BottomLeftMenu, bottomCentreMenu, bottomRightMenu);
    }

    private static void createBottomLeftMenu(VBox bottomCentreMenu)
    {

            // bottomCentreMenu.getChildren().add();
    }

    private static void createBottomCentreMenu(HBox bottomLeftMenu)
    {
        for (int i = 0; i < NUMBER_OF_UNITS; i++)
        {
            VBox unitContainer = new VBox();
            unitContainer.setPrefSize((float) BC_MENU_WIDTH / NUMBER_OF_UNITS, BOTTOM_MENU_HEIGHT);
            bottomLeftMenu.getChildren().add(unitContainer);
        }

    }


}
