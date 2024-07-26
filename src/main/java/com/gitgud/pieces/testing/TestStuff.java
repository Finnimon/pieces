package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.TerrainType;
import com.gitgud.engine.view.GridMapRender;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.activeGame.GameLoader;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.Core;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.gitgud.pieces.testing.Missions.MISSION0;


public class TestStuff
{
    public static void main(String[] args)
    {
    }
    
    
    public static <T extends GridMappable> GridMap<T> getTestMap(int width, int height)
    {
        TerrainType[][] terrainTypes = new TerrainType[height][width];
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                terrainTypes[y][x] = TerrainType.values()[(x * y) % 2];
            }
        }
        
        return GridMap.create(terrainTypes);
    }
    
    
    public static boolean[][] booleanArray(int width, int height)
    {
        boolean[][] array = new boolean[height][width];
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                array[y][x] = Core.roll(0.9f);
            }
        }
        
        return array;
    }
    
    
    //    public static void jsonTestFinnK(String[] args) throws IOException, ClassNotFoundException
    //    {
    //        // FightAgent --- OK
    //        // Modifier --- OK
    //        // Spell --- OK
    //
    //        // Insert resources here
    //        JsonArray fightAgentTypes = AssetParser.parseJsonArray(Strings.FIGHT_AGENT_TYPES);
    //        // Insert Json Objects here
    //        JsonObject fightAgentJson = fightAgentTypes.get(0).getAsJsonObject();
    //
    //        // Register type adapters
    //        GsonBuilder gsonBuilder = new GsonBuilder();
    //        // insert type adapters here
    //
    //        Gson gson = gsonBuilder.create();
    //
    //
    //        // Deserialization
    //        // FightAgent fightAgent = gson.fromJson(fightAgentJson, FightAgent.class);
    //        // Serialization
    //
    //
    //        // Test output
    //
    //
    //        // Write Json file
    //        FileOutputStream writableFile = new FileOutputStream("src/main/resources/com/gitgud/gameObjectTypes/test.json");
    //        // Insert Json objects to write here
    //        // writableFile.write(hashMapToJson.getBytes());
    //        writableFile.close();
    //    }
    
    
    public static void lindigTest(Stage stage)
    {
        //        ActiveGameController.initialize();
        lindigJsonTest();
        //        ActiveGameController.getInstance();
        //        GameFlow.showNextScene();
    }
    
    
    private static void lindigJsonTest()
    {
        GameLoader gameLoader = new GameLoader();
        //        gameLoader.save();
        gameLoader.loadSaveFile("TestPlayer");
        //        GameFlow.showNextScene();
        //        Gson gson = FxGson.create();
        //        JsonElement jsonElement= gson.toJsonTree(object);;
        //        System.out.println(jsonElement.toString());
        //        Object object2 = gson.fromJson(jsonElement.getAsJsonObject().get(name), object.getClass());
        //        try
        //        {
        //            Files.writeString(new File("src/" + name + ".json").toPath(), jsonElement.getAsJsonObject().toString());
        //        }
        //        catch (IOException e)
        //        {
        //            throw new RuntimeException(e);
        //        }
        //        System.out.println("success");
    }
    
    
    private static void testMissionController()
    {
        Mission mission = MISSION0;
        MissionController missionController = new MissionController(mission);
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
}
