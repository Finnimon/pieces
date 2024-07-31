package com.gitgud.pieces.utility.gsonSerializationDeprecated;

import com.gitgud.engine.model.map.GridMap;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.fight.FightTimeLine;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;

@Deprecated
public class FightSerializer implements JsonSerializer<Fight>, JsonDeserializer<Fight>
{
    private static final String GRID_MAP = "gridMap";
    
    
    private static final String OWNERSHIP_MAP = "ownershipMap";
    
    
    private static final String FIGHT_TIMELINE = "fightTimeLine";
    
    
    @Override
    public Fight deserialize(JsonElement src, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        
        JsonObject jsonObject = src.getAsJsonObject();
        
        Fight fight = SilentObjectCreator.create(Fight.class);
        
        Field[] fields = fight.getClass().getDeclaredFields();
        
        for (Field field : fields)
        {
            field.setAccessible(true);
            
            try
            {
                switch (field.getName())
                {
                    case GRID_MAP:
                        field.set(fight, context.deserialize(jsonObject.get(GRID_MAP), GridMap.class));
                        break;
                    
                    case OWNERSHIP_MAP:
                        field.set(fight, deserializeOwnershipMap(jsonObject.get(OWNERSHIP_MAP).getAsJsonObject()));
                        break;
                    
                    case FIGHT_TIMELINE:
                        field.set(fight, context.deserialize(jsonObject.get(FIGHT_TIMELINE), FightTimeLine.class));
                        break;
                    
                    default:
                        field.set(fight, context.deserialize(jsonObject.get(field.getName()), field.getType()));
                        break;
                }
            }
            catch (IllegalAccessException e)
            {
            
            }
        }
        
        return null;
    }
    
    
    private HashMap<Player, HashSet<FightAgent>> deserializeOwnershipMap(JsonObject mapJson)
    {
        HashMap<Player, HashSet<FightAgent>> ownershipMap = new HashMap<>();
        
        
        return ownershipMap;
    }
    
    
    @Override
    public JsonElement serialize(Fight src, Type type, JsonSerializationContext context)
    {
        
        
        return null;
    }
}
