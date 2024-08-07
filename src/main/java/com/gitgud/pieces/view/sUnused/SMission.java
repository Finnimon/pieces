package com.gitgud.pieces.view.sUnused;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.mission.Mission;
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
    public static Scene createMissionScene(Stage stage) //todo GridMap gridMap, FightAgent[] activeFightFigures
    {
        Mission mission = null; //todo
        GridMap<GameObject> gridMap = mission.getGridMap(); //todo
        
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
        createPlayerPosition(playerAgentRender);
        mainMap.setContent(gameObjektGroup);
        
        HBox topMenu = new HBox();
        topMenu.setMinHeight(TOP_MENU_HEIGHT);
        createTopMenu(topMenu);
        
        HBox bottomMenu = new HBox();
        bottomMenu.setMinHeight(BOTTOM_MENU_HEIGHT);
        createBottomMenu(bottomMenu);
        
        mapMenu.setTop(topMenu);
        mapMenu.setBottom(bottomMenu);
        mapMenu.setCenter(mainMap);
        
        root.getChildren().add(mapMenu);
        return MapScene;
    }
    
    
    protected static void createPlayerPosition(Group playerAgentRender)
    {
        AnchorPane anchorPane = new AnchorPane();
        //todo Player position und mögliche bewegungen
        playerAgentRender.getChildren().add(anchorPane);
    }
    
    
    protected static void createBottomMenu(HBox menu)
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
    
    
    protected static void createBottomLeftMenu(VBox bottomCentreMenu)
    {
        
        bottomCentreMenu.getChildren().add(null);//todo
    }
    
    
    protected static void createBottomCentreMenu(HBox bottomLeftMenu)
    {
        Mission mission = null;//todo
        FightAgent[] activeFightFigures = mission.getActiveFightAgents();
        
        for (int i = 0; i < NUMBER_OF_UNITS; i++)
        {
            VBox unitContainer = new VBox();
            unitContainer.setPrefSize((float) BC_MENU_WIDTH / NUMBER_OF_UNITS, BOTTOM_MENU_HEIGHT);
            
            Image fightFigureSprite = new Image(activeFightFigures[i].getSpriteUrl());
            ImageView viewFightFigureSprite = new ImageView(fightFigureSprite);
            
            bottomLeftMenu.getChildren().addAll(unitContainer, viewFightFigureSprite);
        }
    }
    
}
