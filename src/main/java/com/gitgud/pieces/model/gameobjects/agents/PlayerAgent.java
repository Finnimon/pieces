package com.gitgud.pieces.model.gameobjects.agents;


import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.agent.Agent;
import com.gitgud.pieces.model.gameobjects.Faction;


public class PlayerAgent extends Agent
{
    private static final boolean IS_FLYING = false;
    
    
    private static final int MOVEMENT_RANGE = 7;
    
    
    private static final String NAME_SUFFIX = " King";
    
    
    private static final String DESCRIPTION = "Once a just king. Now broken by many defensive battles he marches forth to retake all his lands.";
    
    
    private static final String SPRITE_PATH_PREFIX = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\agents\\";
    
    
    private static final String SPRITE_PATH_SUFFIX = "\\black_king.png";
    
    
    private static final Faction DEFAULT_FACTION = Faction.MONOCHROME;
    
    
    public PlayerAgent()
    {
        this(DEFAULT_FACTION);
    }
    
    
    public PlayerAgent(Faction faction)
    {
        super(determineName(faction), DESCRIPTION, determineSpriteUrl(faction), IS_FLYING, MOVEMENT_RANGE);
    }
    
    
    private static String determineName(Faction faction)
    {
        return Named.formatString(faction.name()) + NAME_SUFFIX;
    }
    
    
    private static String determineSpriteUrl(Faction faction)
    {
        return SPRITE_PATH_PREFIX + faction.name() + SPRITE_PATH_SUFFIX;
    }
}
