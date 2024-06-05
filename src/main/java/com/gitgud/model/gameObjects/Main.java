package com.gitgud.model.gameObjects;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;
import com.gitgud.utility.gsonSerialization.AssetParser;
import com.gitgud.utility.gsonSerialization.FightAgentDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Main
{
    public static void main (String[] args)
    {
        JsonObject pawn = AssetParser.parseJsonArray(AssetLocator.FIGHT_AGENT_TYPES).get(0).getAsJsonObject();

        FightAgentDeserializer deserializer = new FightAgentDeserializer();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FightAgent.class, deserializer);
        Gson gson = gsonBuilder.create();

        FightAgent pawnFightAgent = gson.fromJson(pawn, FightAgent.class);

        System.out.println(pawnFightAgent.getHealth());
    }
}
