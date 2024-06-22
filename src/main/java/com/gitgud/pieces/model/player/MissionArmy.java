package com.gitgud.pieces.model.player;

import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


public class MissionArmy
{
    private final FightAgent[] activeFightFigures;
    
    
    private final FightAgent[] discard;
    
    
    private MissionArmy(FightAgent[] activeFightFigures, FightAgent[] discard)
    {
        this.activeFightFigures = activeFightFigures;
        this.discard = discard;
    }
    
    
    public static MissionArmy create()
    {
        return new MissionArmy(new FightAgent[5], new FightAgent[5]);
    }
    
    
    public static MissionArmy create(FightAgent[] activeFightFigures, FightAgent[] discard)
    {
        return new MissionArmy(activeFightFigures, discard);
    }
    
    
    public FightAgent[] getActiveFightUnits()
    {
        return activeFightFigures;
    }
    
    
    public FightAgent[] getDiscard()
    {
        return discard;
    }
}
