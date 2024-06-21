package com.gitgud.pieces.utility.gsonSerialization;


import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.google.gson.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;


public class SpellSerializer implements JsonSerializer<Spell>
{
    private static final String TYPE = "type";
    
    
    private static final String MODIFIER = "modifier";
    
    
    private static final String ATTACK = "FightAgentAttackModifier";
    
    
    private static final String DEFENCE = "FightAgentDefenceModifier";
    
    
    private static final String HEALTH = "FightAgentHealthModifier";
    
    
    private static final String MANA = "FightAgentManaModifier";
    
    
    @Override
    public JsonElement serialize(Spell src, Type type, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        Field[] fields = src.getClass().getDeclaredFields();
        
        try
        {
            for (Field field : fields)
            {
                field.setAccessible(true);
                
                switch (field.getName())
                {
                    case TYPE:
                        jsonObject.add(TYPE, context.serialize(src.getType().getAsString()));
                        break;
                    
                    case MODIFIER:
                        serializeModifier(jsonObject, src, context);
                        break;
                    
                    default:
                        jsonObject.add(field.getName(), context.serialize(field.get(src)));
                }
            }
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        
        return jsonObject;
    }
    
    
    private void serializeModifier(JsonObject jsonObject, Spell src, JsonSerializationContext context)
    {
        JsonObject modifierCollection = new JsonObject();
        
        Gson gson = new Gson();
        
        for (Modifier<FightAgent> modifier : src.getModifier().getModifiers())
        {
            switch (modifier.getClass().getSimpleName())
            {
                case ATTACK:
                    modifierCollection.add(ATTACK, gson.toJsonTree(modifier));
                    break;
                
                case DEFENCE:
                    modifierCollection.add(DEFENCE, gson.toJsonTree(modifier));
                    break;
                
                case HEALTH:
                    modifierCollection.add(HEALTH, gson.toJsonTree(modifier));
                    break;
                
                case MANA:
                    modifierCollection.add(MANA, gson.toJsonTree(modifier));
                    break;
            }
        }
        
        jsonObject.add(MODIFIER, modifierCollection);
    }
}
