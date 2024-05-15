package com.gitgud.view;

import com.gitgud.control.PlayerController;
import com.gitgud.model.map.TerrainType;
import com.gitgud.model.map.Tile;
import com.gitgud.model.player.Player;
import com.gitgud.model.player.ResourceType;
import com.gitgud.model.player.Wallet;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;


public class SMap{

    /**
     * This Method creates the Map-scene including: the Feld of Tiles, the Move transition methods, The Inventory,
     * Methods for displaying the game objects
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    public static Scene createMapScene(Stage stage)
    {
        Group root = new Group();
        Scene MapScene = new Scene(root);
        stage.setScene(MapScene);

        BorderPane mapMenue = new BorderPane();
        ScrollPane mainMap = new ScrollPane();
        mainMap.setPrefSize(1000,600);

        Group tilesGroup = new Group();
        //createFieldOfTiles(tilesGroup);
        mainMap.setContent(tilesGroup);

        Group gameObjektGroup = new Group();
        //createFieldOfGameObjekts(gameObjektGroup, );
        mainMap.setContent(gameObjektGroup);

        HBox topMenue = new HBox();
        topMenue.setMinHeight(100);
        createTopMenu(topMenue);

        HBox bottomMenue = new HBox();
        bottomMenue.setMinHeight(300);
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
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    public static void createFieldOfTiles(Group tilesGroup, Tile[] tiles)
    {
        {
            for (Tile tile : tiles)
            {
                Rectangle rectangle = new Rectangle();
                int xPosition = tile.xPosition();
                int yPosition = tile.yPosition();
                rectangle.setX(xPosition * 110);
                rectangle.setY(yPosition * 110);
                rectangle.setWidth(100);
                rectangle.setHeight(100);

                if(tile.terrain().terrainType() == TerrainType.MOUNTAIN)
                {
                    rectangle.setFill(Color.GRAY);
                }
                else if((xPosition + yPosition) % 2 == 1 )
                {
                    rectangle.setFill(Color.SADDLEBROWN);
                }
                else
                {
                    rectangle.setFill(Color.WHEAT);
                }
                rectangle.setOnMouseClicked(event ->
                {
                    if (event.getButton() == MouseButton.SECONDARY)
                    {
                        moveRequest(rectangle);
                    }

                });
                tilesGroup.getChildren().add(rectangle);
            }
        }
    }

    public static void createFieldOfGameObjekts(Group tilesGroup)
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                Rectangle tile = new Rectangle();
                tile.setY(i * 110);
                tile.setX(j * 110);
                tile.setWidth(100);
                tile.setHeight(100);
                if((i + j) % 2 == 1 )
                {
                    tile.setFill(Color.SADDLEBROWN);
                }
                else
                {
                    tile.setFill(Color.WHEAT);
                }
                tile.setOnMouseClicked(event ->
                {
                    if (event.getButton() == MouseButton.SECONDARY)
                    {
                        moveRequest(tile);
                    }

                });
                tilesGroup.getChildren().add(tile);
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

       HashMap<ResourceType,Long> resourceMap = wallet.resourceMap();
        for (ResourceType key : resourceMap.keySet())
        {
            long value = resourceMap.get(key);

            HBox box = new HBox();

        }

        menu.getChildren().addAll();
    }

    private static void createBottomMenu(HBox menu)
    {

    }


}
