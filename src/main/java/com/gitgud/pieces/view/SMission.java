package com.gitgud.pieces.view;

import com.gitgud.engine.model.gameObject.interactable.Interactable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.gameObjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.testing.TestStuff;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SMission extends SGridMap implements IDimentions
{
    /**
     * This Method creates the Mission-scene including: the Feld of Tiles, the Move transition methods, The Inventory,
     * Methods for displaying the game objects.
     *
     * @author Julius R.
     * @Owner: Julius R.
     * @Since: 01.05.2024
     * @Version: 1.0
     */
    public static Scene createMissionScene(Stage stage)
    {
        //todo Mission mission = ActiveGameController.getInstance().get().getMission();
        //todo GridMap<Interactable> gridMap = mission.getGridMap();
        GridMap<Interactable> gridMap = TestStuff.getTestMap(10, 10); // todo testsachen entfernen
        Mission mission = TestStuff.getMission();

        Group root = new Group();
        Scene MapScene = new Scene(root);
        stage.setScene(MapScene);
        
        BorderPane mapMenu = new BorderPane();
        ScrollPane mainMap = new ScrollPane();
        mainMap.setPrefSize(STAGE_WIDTH, MAIN_MAP_HEIGHT);
        
        Group tilesGroup = new Group();
        createFieldOfTiles(tilesGroup, gridMap);
        mainMap.setContent(tilesGroup);
        
        Group gameObjektGroup = new Group();
        createFieldOfGameObjects(gameObjektGroup, gridMap);
        mainMap.setContent(gameObjektGroup);
        
        Group playerAgentRender = new Group();
        createPlayerPosition(playerAgentRender, mission);
        mainMap.setContent(playerAgentRender);
        
        HBox topMenu = new HBox();
        topMenu.setMinHeight(TOP_MENU_HEIGHT);
        createTopMenu(topMenu);
        
        HBox bottomMenu = new HBox();
        bottomMenu.setMinHeight(BOTTOM_MENU_HEIGHT);
        createBottomMenu(bottomMenu, mission);
        
        mapMenu.setTop(topMenu);
        mapMenu.setBottom(bottomMenu);
        mapMenu.setCenter(mainMap);
        
        root.getChildren().add(mapMenu);
        return MapScene;
    }
    
    
    protected static void createBottomMenu(HBox menu, Mission mission)
    {
        VBox bottomCentreMenu = new VBox();
        bottomCentreMenu.setPrefSize(BC_MENU_WIDTH, BOTTOM_MENU_HEIGHT);
        HBox BottomLeftMenu = new HBox();
        BottomLeftMenu.setPrefSize(BS_MENU_WIDTH, BOTTOM_MENU_HEIGHT);
        TextField bottomRightMenu = new TextField(MOVE_TRANSITION_INFO);
        bottomRightMenu.setPrefSize(BS_MENU_WIDTH, BOTTOM_MENU_HEIGHT);
        
        createBottomLeftMenu(bottomCentreMenu);
        createBottomCentreMenu(BottomLeftMenu, mission);
        menu.getChildren().addAll(BottomLeftMenu, bottomCentreMenu, bottomRightMenu);
    }
    
    
    protected static void createBottomLeftMenu(VBox bottomLeftMenu)
    {
        
        bottomLeftMenu.getChildren().add(null);//todo
    }
    
    
    protected static void createBottomCentreMenu(HBox bottomCentreMenu, Mission mission)
    {
        FightAgent[] activeFightFigures = mission.getActiveFightAgents();
        
        for (int i = 0; i < NUMBER_OF_UNITS; i++)
        {
            VBox unitContainer = new VBox();
            unitContainer.setPrefSize((float) BC_MENU_WIDTH / NUMBER_OF_UNITS, BOTTOM_MENU_HEIGHT);
            
            Image fightFigureSprite = new Image(activeFightFigures[i].getSpriteUrl());
            ImageView viewFightFigureSprite = new ImageView(fightFigureSprite);
            
            bottomCentreMenu.getChildren().addAll(unitContainer, viewFightFigureSprite);
        }
    }
    
    
    protected static void createPlayerPosition(Group playerAgentRender, Mission mission)
    {
        VBox playerContainer = new VBox();
        playerContainer.setPrefSize(TILE_DIMENSIONS, TILE_DIMENSIONS);
        AnchorPane.setTopAnchor(playerContainer, (mission.getPlayerAgentPosition().getY() * TILE_SPACING));
        AnchorPane.setLeftAnchor(playerContainer, (mission.getPlayerAgentPosition().getX() * TILE_SPACING));
        mission.getPlayerAgent().getSpriteUrl();
        Image playerSprite = new Image(mission.getPlayerAgent().getSpriteUrl());
        ImageView imageView = new ImageView(playerSprite);

        playerContainer.getChildren().add(imageView);
        playerAgentRender.getChildren().add(playerContainer);

    }
    
}
