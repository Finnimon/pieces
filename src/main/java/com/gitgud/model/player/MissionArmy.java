package com.gitgud.model.player;

import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;


public class MissionArmy
{
    private final FightAgentFL[] activeFightFigures;
    
    
    private final FightAgentFL[] discard;
    
    
    private MissionArmy(FightAgentFL[] activeFightFigures, FightAgentFL[] discard)
    {
        this.activeFightFigures = activeFightFigures;
        this.discard = discard;
    }
    
    
    public static MissionArmy create()
    {
        return new MissionArmy(new FightAgentFL[5], new FightAgentFL[5]);
    }
    
    
    public static MissionArmy create(FightAgentFL[] activeFightFigures, FightAgentFL[] discard)
    {
        return new MissionArmy(activeFightFigures, discard);
    }
    
    
    public FightAgentFL[] getActiveFightUnits()
    {
        return activeFightFigures;
    }
    
    
    public FightAgentFL[] getDiscard()
    {
        return discard;
    }
}
