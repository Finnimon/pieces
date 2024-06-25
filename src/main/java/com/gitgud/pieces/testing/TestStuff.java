package com.gitgud.pieces.testing;

import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.TerrainType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Difficulty;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.utility.Core;
import com.gitgud.pieces.utility.Strings;
import com.gitgud.pieces.utility.gsonSerialization.SilentObjectCreator;
import com.gitgud.pieces.utility.services.AssetParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class TestStuff
{
    // Main method for testing
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        jsonTestFinnK(args);
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


    public static void jsonTestFinnK (String[] args) throws IOException, ClassNotFoundException
    {
        // FightAgent --- OK
        // Modifier --- OK
        // Spell --- OK

        // Insert resources here
        JsonArray fightAgentTypes = AssetParser.parseJsonArray(Strings.FIGHT_AGENT_TYPES);
        // Insert Json Objects here
        JsonObject fightAgentJson = fightAgentTypes.get(0).getAsJsonObject();

        // Register type adapters
        GsonBuilder gsonBuilder = new GsonBuilder();
        // insert type adapters here

        Gson gson = gsonBuilder.create();


        // Deserialization
        FightAgent fightAgent = gson.fromJson(fightAgentJson, FightAgent.class);
        // Serialization


        // Test output


        // Write Json file
        FileOutputStream writableFile = new FileOutputStream("src/main/resources/com/gitgud/gameObjectTypes/test.json");
        // Insert Json objects to write here
        // writableFile.write(hashMapToJson.getBytes());
        writableFile.close();

        System.out.println(Difficulty.EASY.name());

    }


    
}
