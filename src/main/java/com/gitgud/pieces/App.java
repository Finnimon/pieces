package com.gitgud.pieces;


import com.gitgud.engine.control.StageController;
import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.pieces.utility.Strings;
import com.gitgud.pieces.utility.services.AssetParser;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.pieces.control.JsonParser;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.gameobjects.AssetLocator;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.FightAgentCollectable;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.testing.Missions;
import com.gitgud.pieces.testing.TestStuff;
import com.gitgud.pieces.utility.gsonSerialization.*;
import com.gitgud.pieces.view.SMainMenue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;


public class App extends Application
{
    
    
    public static final String ICON_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\agents\\monochrome\\black_king.png";
    
    
    public static final String APP_TITLE = "Pieces";
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        launch();
    }
    
    
    public static void delfiMain(Stage stage)
    {
        stage.setHeight(1000);
        stage.setWidth(1000);
        stage.setScene(SMainMenue.createMainMenueScene(stage));
    }

    
    
    
    public static void jsonTestFinnK (String[] args) throws IOException, ClassNotFoundException
    {
        // FightAgent --- OK
        // Modifier --- OK
        // Spell --- OK

        // Insert resources here
        JsonArray fightAgentTypes = AssetParser.parseJsonArray(Strings.FIGHT_AGENT_TYPES);
        // Insert Json Objects here

        // Register type adapters
        GsonBuilder gsonBuilder = new GsonBuilder();
        // insert type adapters here

        Gson gson = gsonBuilder.create();

        // Deserialization

        // Serialization

        // Test output


        // Write Json file
        FileOutputStream writableFile = new FileOutputStream("src/main/resources/com/gitgud/gameObjectTypes/test.json");
        // Insert Json objects to write here
        // writableFile.write(spellToJson.getBytes());
        writableFile.close();
    }
    
    
    public static void finnTest(Stage stage)
    {
        testMissionController();
        //        addTestGridMapRenderToStage(stage);
        //        finnGsonTest();
    }
    
    
    private static void testMissionController()
    {
        Stage stage = StageController.getInstance().getStage();
        Mission mission = Missions.FIRST;
        MissionController missionController = new MissionController(mission);
        Scene scene = new Scene(missionController.getRender());

        stage.setScene(scene);
        stage.show();
        missionController.start();
    }
    
    
    private static void addTestGridMapRenderToStage(Stage stage)
    {
        GridMap<GameObject> testMap = TestStuff.getTestMap(12, 12);
        
        GridMapRender<GridMappable> gridMapRender = new GridMapRender<>(testMap, 90);
        
        AnchorPane group = new AnchorPane();
        gridMapRender = new GridMapRender<GridMappable>(Missions.FIRST.getGridMap(), 90);
        ScrollPane scrollPane = new ScrollPane(gridMapRender);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        group.getChildren().add(scrollPane);
        scrollPane.setMaxWidth(1000);
        PlayerAgent playerAgent = new PlayerAgent(Faction.PINK);
        gridMapRender.addGridMappable(playerAgent, Missions.FIRST.getGridMap().getVertex(0, 0));
        
        Scene scene = new Scene(group);
        group.setMinWidth(1200);
        AnchorPane.setLeftAnchor(scrollPane, 200d);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
        
        gridMapRender.relocateGridMappable(playerAgent, Missions.FIRST.getGridMap().getVertex(5, 5));
        gridMapRender.addGridMappable(new PlayerAgent(), Missions.FIRST.getGridMap().getVertex(0, 0));
        gridMapRender.addGridMappable(new PlayerAgent(), Missions.FIRST.getGridMap().verticeSet().last());
        
    }
    
    
    private static final void finnGsonTest()
    {
        
        GridMap<GameObject> testMap = TestStuff.getTestMap(12, 12);
        Gson gson = JsonParser.getInstance().getGson();
        
        String json = gson.toJson(testMap, GridMap.class);
        System.out.println(json);
        GridMap<GameObject> gridMap = gson.fromJson(json, GridMap.class);
        System.out.println(gridMap == testMap);
    }
    
    
    private void initialize(Stage stage)
    {
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.centerOnScreen();
        StageController.initialize(stage);
        setTitleAndIcon();
    }
    
    
    private void setTitleAndIcon()
    {
        Stage stage = StageController.getInstance().getStage();
        stage.setTitle(APP_TITLE);
        try
        {
            stage.getIcons().add(new Image(Sprite.urlFromFilePath(ICON_PATH)));
        }
        catch (MalformedURLException ignored) //can be ignored because worst case there is no Icon
        {
        }
    }
    
    
    @Override
    public void start(Stage stage) throws IOException
    {
        initialize(stage);
        finnTest(stage);
        //        delfiMain(stage);
        stage.show();
        
    }
}