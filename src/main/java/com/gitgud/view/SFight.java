package com.gitgud.view;

import com.gitgud.model.fight.Fight;
import com.gitgud.model.fight.FightTimeLine;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.TreeSet;

public class SFight extends SGridMap implements IDimentions {


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
        GridMap<FightAgent> gridMap = fight.getGridMap();

        Group root = new Group();
        Scene MapScene = new Scene(root);
        stage.setScene(MapScene);

        BorderPane mapMenue = new BorderPane();
        ScrollPane mainMap = new ScrollPane();
        mainMap.setPrefSize(STAGE_WIDTH, MAIN_MAP_HEIGHT);

        Group tilesGroup = new Group();
        createFieldOfTiles(tilesGroup, gridMap);
        mainMap.setContent(tilesGroup);

        Group fightFigureGroup = new Group();
        createFieldOfGameObjects(fightFigureGroup, gridMap);
        mainMap.setContent(fightFigureGroup);

        HBox topMenue = new HBox();
        topMenue.setMinHeight(TOP_MENU_HEIGHT);
        createTopMenu(topMenue);

        HBox FightTimelineMenu = new HBox();
        FightTimelineMenu.setMinHeight(BOTTOM_MENU_HEIGHT);
        createFightTimelineMenu(FightTimelineMenu);
        ScrollPane scrollPane = new ScrollPane(FightTimelineMenu);

        mapMenue.setTop(topMenue);
        mapMenue.setBottom(scrollPane);
        mapMenue.setCenter(mainMap);

        root.getChildren().add(mapMenue);
        return MapScene;
    }

    private static void createFightTimelineMenu(HBox fightTimelineMenu)
    {
        FightTimeLine fightTimeLine = null;//todo
        TreeSet<FightAgent> activeFightFigures = fightTimeLine.getCurrent();

        for (FightAgent fightAgent : activeFightFigures)
        {
            VBox fightFigureContainer = new VBox();
            Image fightFigureSprite = new Image(fightAgent.getSpriteUrl());
            ImageView viewFightFigureSprite = new ImageView(fightFigureSprite);
            fightFigureContainer.getChildren().add(viewFightFigureSprite);
            fightTimelineMenu.getChildren().add(fightFigureContainer);
        }
    }
}
