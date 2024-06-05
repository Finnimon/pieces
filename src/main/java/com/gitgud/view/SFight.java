package com.gitgud.view;

import com.gitgud.model.fight.Fight;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.model.map.GridMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SFight extends SMission implements IDimentions {


    /**
     * This Method creates the Mission-scene including: the Feld of Tiles, the Move transition methods, The Inventory,
     * Methods for displaying the game objects.
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    public static Scene createFightScene(Stage stage) // todo GridMap gridMap, FightAgent[] activeFightFigures
    {
        Fight fight = null;
        GridMap<FightAgent> gridMap= fight.getGridMap();

        Group root = new Group();
        Scene MapScene = new Scene(root);
        stage.setScene(MapScene);

        BorderPane mapMenue = new BorderPane();
        ScrollPane mainMap = new ScrollPane();
        mainMap.setPrefSize(STAGE_WIDTH, MAIN_MAP_HEIGHT);

        Group tilesGroup = new Group();
        createFieldOfTiles(tilesGroup, gridMap);
        mainMap.setContent(tilesGroup);

        Group gameObjektGroup = new Group();
        createFieldOfGameObjects(gameObjektGroup, gridMap);
        mainMap.setContent(gameObjektGroup);

        Group playerAgentRender = new Group();
        createPlayerPosition(playerAgentRender);
        mainMap.setContent(gameObjektGroup);

        HBox topMenue = new HBox();
        topMenue.setMinHeight(TOP_MENU_HEIGHT);
        createTopMenu(topMenue);

        HBox FightTimelineMenu = new HBox();
        FightTimelineMenu.setMinHeight(BOTTOM_MENU_HEIGHT);
        createFightTimelineMenu(FightTimelineMenu);

        mapMenue.setTop(topMenue);
        mapMenue.setBottom(FightTimelineMenu);
        mapMenue.setCenter(mainMap);

        root.getChildren().add(mapMenue);
        return MapScene;
    }
    private static void createFightTimelineMenu(HBox FightTimelineMenu)
    {

    }

}
