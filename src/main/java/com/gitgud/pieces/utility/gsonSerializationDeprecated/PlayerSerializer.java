package com.gitgud.pieces.utility.gsonSerializationDeprecated;

import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Deprecated
public class PlayerSerializer implements JsonSerializer<Player>, JsonDeserializer<Player>
{
    private static final String NAME = "name";


    private static final String DIFFICULTY = "difficulty";


    private static final String ARMY = "army";


    @Override
    public Player deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws
    JsonParseException
    {
        JsonObject jsonObject = src.getAsJsonObject();

        String name = jsonObject.get(NAME).toString();
//        Difficulty difficulty = Difficulty.fromString(jsonObject.get(DIFFICULTY).toString());
//        Army army = deserializeArmy(jsonObject.getAsJsonObject(ARMY), context);
        Wallet wallet;
        ArtefactPouch artefactPouch;
        SpellBook spellBook;

        return null;
    }

//
//    private Army deserializeArmy(JsonObject armyJson, JsonDeserializationContext context)
//    {
//        JsonArray army = armyJson.getAsJsonArray();
//        HashMap<FightAgentType, HashSet<FightAgent>> baseCampStash = new HashMap<>();
//        ArrayList<FightAgentType> fightAgentTypes = new ArrayList<>();
//
//        return null;
//    }


    @Override
    public JsonElement serialize(Player src, Type typeOfSrc, JsonSerializationContext context)
    {
        return null;
    }
}
