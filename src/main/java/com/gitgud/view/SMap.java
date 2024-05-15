package com.gitgud.view;

import com.gitgud.control.PlayerController;
import com.gitgud.model.map.TerrainType;
import com.gitgud.model.map.Tile;
import com.gitgud.model.player.Player;
import com.gitgud.model.player.ResourceType;
import com.gitgud.model.player.Wallet;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;


public class SMap{

    private static String ICH_HASSE_MEIN_LEBEN = "FICKMICH";

    private static final int STAGE_WITH = 1000;
    private static final int MAIN_MAP_HEIGHT = 600;
    private static final int TOP_MENU_HEIGHT = 100;
    private static final int BOTTOM_MENU_HEIGHT = 300;
    private static final int TILE_DIMENSIONS = 100;
    private static final int TILE_SPACING = 110;
    private static final int STAGE_WITH = 1000;
    private static final int STAGE_WITH = 1000;
    private static final int STAGE_WITH = 1000;
    private static final int STAGE_WITH = 1000;
    private static final int STAGE_WITH = 1000;
    private static final String MOVE_TRANSITION_INFO = "Press 1 to skip the turn and get more steps on the next turn,\n"
            + "Press 2 to skip the turn and regenerate a little mana,\n"
            + "Press 3 to skip turn and heal your units,";

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
        mainMap.setPrefSize(STAGE_WITH,MAIN_MAP_HEIGHT);

        Group tilesGroup = new Group();
        createFieldOfTiles(tilesGroup);
        mainMap.setContent(tilesGroup);

        Group gameObjektGroup = new Group();
        createFieldOfGameObjekts(gameObjektGroup, );
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
                rectangle.setX(xPosition * TILE_SPACING);
                rectangle.setY(yPosition * TILE_SPACING);
                rectangle.setWidth(TILE_DIMENSIONS);
                rectangle.setHeight(TILE_DIMENSIONS);

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
        HBox BottomLeftMenu = new HBox();
        TextField bottomRightMenu = new TextField(MOVE_TRANSITION_INFO);

        createBottomLeftMenu(bottomCentreMenu);
        createBottomCentreMenu(BottomLeftMenu);
        menu.getChildren().addAll(BottomLeftMenu, bottomCentreMenu, bottomRightMenu);
    }
    private static void createBottomLeftMenu(VBox bottomCentreMenu)
    {

    }
    private static void createBottomCentreMenu(HBox bottomLeftMenu)
    {

    }


}
