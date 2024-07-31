package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.TerrainType;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.control.game.Game;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.Core;
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
    //        FileOutputStream writableFile = new FileOutputStream
    //        ("src/main/resources/com/gitgud/gameObjectTypes/test.json");
    //        // Insert Json objects to write here
    //        // writableFile.write(hashMapToJson.getBytes());
    //        writableFile.close();
    //    }
    
    
    public static void lindigTest(Stage stage)
    {
        ActiveGameController.testInitialize();
        Game.Flow.showNextScene();
                        lindigJsonTest();
        //        ActiveGameController.getInstance();
        //        Game.Flow.showNextScene();
    }
    
    
    private static void lindigJsonTest()
    {
//                Game.Saver.save();
        //        Game.Flow.showNextScene();
        Game.Loader.load("TestPlayer");
        //        Game.Loader.load("TestPlayer");
        //        GameFlow.showNextScene();
        //        Gson gson = FxGson.create();
        //        JsonElement jsonElement= gson.toJsonTree(object);;
        //        System.out.println(jsonElement.toString());
        //        Object object2 = gson.fromJson(jsonElement.getAsJsonObject().get(name), object.getClass());
        //        try
        //        {
        //            Files.writeString(new File("src/" + name + ".json").toPath(), jsonElement.getAsJsonObject()
        //            .toString());
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
}
