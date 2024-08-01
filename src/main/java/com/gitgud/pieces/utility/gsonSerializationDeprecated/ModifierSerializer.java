package com.gitgud.pieces.utility.gsonSerializationDeprecated;


import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.utility.modification.fightAgent.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;


@Deprecated
public class ModifierSerializer implements JsonSerializer<Modifier<Type>>, JsonDeserializer<Modifier<Type>>
{
    private static final String FIGHT_AGENT = "FightAgentModifier";
    
    
    private static final String ATTACK = "FightAgentAttackModifier";
    
    
    private static final String DEFENCE = "FightAgentDefenceModifier";
    
    
    private static final String HEALTH = "FightAgentHealthModifier";
    
    
    private static final String MANA = "FightAgentManaModifier";
    
    
    private static final String MOVEMENT = "FightAgentMovementModifier";
    
    
    private static final String DURATION = "DurableFightAgentModifier";
    
    
    @Override
    public JsonElement serialize(Modifier<Type> src, Type type, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        
        switch (src.getClass().getSimpleName())
        {
            case FIGHT_AGENT:
                serializeFightAgentModifier(jsonObject, src, context);
                break;
            
            case DURATION:
                serializeDurableFightAgentModifier();
                break;
            
            default:
                jsonObject.add(src.getClass().getSimpleName(), context.serialize(src));
                break;
        }
        
        return jsonObject;
    }
    
    
    private void serializeFightAgentModifier(JsonObject jsonObject, Modifier<?> src, JsonSerializationContext context)
    {
        JsonArray modifiers = new JsonArray();
        FightAgentModifier fightAgentModifier;
        
        if (src instanceof FightAgentModifier)
        {
            fightAgentModifier = (FightAgentModifier) src;
            
            for (Modifier<FightAgent> modifier : fightAgentModifier.getModifiers())
            {
                modifiers.add(context.serialize(modifier, Modifier.class));
            }
        }
        
        jsonObject.add(FIGHT_AGENT, modifiers);
    }
    
    
    private void serializeDurableFightAgentModifier()
    {
    
    }
    
    
    @Override
    public Modifier<Type> deserialize(JsonElement src, Type type, JsonDeserializationContext context)
            throws JsonParseException
    {
        Modifier<?> modifier = null;
        
        for (String key : src.getAsJsonObject().keySet())
        {
            switch (key)
            {
                case FIGHT_AGENT:
                    modifier = deserializeFightAgentModifier(src, context);
                    break;
                
                case ATTACK:
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentAttackModifier.class);
                    break;
                
                case DEFENCE:
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentDefenceModifier.class);
                    break;
                
                case HEALTH:
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentHealthModifier.class);
                    break;
                
                case MANA:
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentManaModifier.class);
                    break;
                
                case MOVEMENT:
                    modifier = context.deserialize(src.getAsJsonObject().get(key), FightAgentMovementModifier.class);
                    break;
                
                default:
                    break;
            }
        }
        
        return (Modifier<Type>) modifier;
    }
    
    
    private FightAgentModifier deserializeFightAgentModifier(JsonElement src, JsonDeserializationContext context)
    {
        Collection<Modifier<FightAgent>> fightAgentModifiers = new ArrayList<>();
        
        for (JsonElement modifier : src.getAsJsonObject().getAsJsonArray(FIGHT_AGENT))
        {
            fightAgentModifiers.add(context.deserialize(modifier, Modifier.class));
        }
        
        return new FightAgentModifier(fightAgentModifiers);
    }
}
