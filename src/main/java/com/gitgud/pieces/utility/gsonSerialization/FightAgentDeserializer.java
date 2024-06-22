package com.gitgud.pieces.utility.gsonSerialization;

import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FightAgentDeserializer implements JsonDeserializer<FightAgent>
{
    private static final String TYPE = "type";
    
    
    private static final String FACTION = "faction";
    
    
    @Override
    public FightAgent deserialize(JsonElement src, Type type,
                                  JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = src.getAsJsonObject();
        
        FightAgent fightAgent = SilentObjectCreator.create(FightAgent.class);
        
        List<Field> fields = new ArrayList<>(List.of(fightAgent.getClass().getDeclaredFields()));
        fields.addAll(Arrays.asList(fightAgent.getClass().getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(fightAgent.getClass().getSuperclass().getSuperclass().getDeclaredFields()));
        
        
        for (Field field : fields)
        {
            field.setAccessible(true);
            
            try
            {
                switch (field.getName())
                {
                    case TYPE:
                        field.set(fightAgent, FightAgentType.fromString(jsonObject.get(TYPE).getAsString()));
                        break;
                    
                    case FACTION:
                        field.set(fightAgent, Faction.fromString(jsonObject.get(FACTION).getAsString()));
                        break;
                    
                    default:
                        field.set(fightAgent, context.deserialize(jsonObject.get(field.getName()), field.getType()));
                }
            }
            catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }
        
        return fightAgent;
    }
}