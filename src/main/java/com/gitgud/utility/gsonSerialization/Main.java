package com.gitgud.utility.gsonSerialization;

import com.gitgud.model.gameObjects.AssetLocator;
import com.gitgud.model.gameObjects.FightAgentType;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        JsonArray jsonArray = AssetParser.parseJsonArray(AssetLocator.FIGHT_AGENT_TYPES);
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FightAgent.class, new FightAgentDeserializer());
        gsonBuilder.registerTypeAdapter(FightAgent.class, new FightAgentSerializer());
        Gson gson = gsonBuilder.create();

        FightAgent fighter = gson.fromJson(jsonObject, FightAgent.class);

        byte[] fighterSerialized = gson.toJson(fighter).getBytes();

        FileOutputStream file = new FileOutputStream("src/main/java/com/gitgud/utility/gsonSerialization/test.json");
        file.write(fighterSerialized);
    }
}
