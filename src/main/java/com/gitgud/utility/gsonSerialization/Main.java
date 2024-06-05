package com.gitgud.utility.gsonSerialization;

import com.gitgud.model.gameObjects.AssetLocator;
import com.gitgud.model.gameObjects.FightAgentType;
import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args)
    {
        JsonArray jsonArray = AssetParser.parseJsonArray(AssetLocator.FIGHT_AGENT_TYPES);
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        Gson gson = new GsonBuilder().registerTypeAdapter(FightAgent.class, new FightAgentDeserializer()).create();

        FightAgent fighter = gson.fromJson(jsonObject, FightAgent.class);
    }
}
