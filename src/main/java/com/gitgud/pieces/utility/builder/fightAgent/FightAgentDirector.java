package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.utility.builder.Builder;
import com.gitgud.pieces.utility.builder.Director;


public class FightAgentDirector implements Director<FightAgent>
{
    
    
    public static final int LEVEL1_MONOCHROME_PAWN_TYPE = FightAgentType.PAWN.typeToInt() + Faction.MONOCHROME.typeToInt() + 1;
    
    
    private Builder<FightAgent> builder;
    
    
    public FightAgentDirector(Builder<FightAgent> builder)
    {
        this.builder = builder;
    }
    
    
    public static int calculateType(Allegiance allegiance, FightAgentType fightAgentType, Faction faction, int rank)
    {
        return allegiance.typeToInt() + fightAgentType.typeToInt() + faction.typeToInt() + rank;
    }
    
    public static Allegiance getAllegiance(int type)
    {
        type /= Allegiance.TYPE_MULTIPLIER;
        
        return Allegiance.values()[type];
    }
    
    public static FightAgentType getFightAgentType(int type)
    {
        type-= getAllegiance(type).typeToInt();
        type /= FightAgentType.TYPE_MULTIPLIER;
        
        return FightAgentType.values()[type];
    }
    
    
    public static Faction getFaction(int type)
    {
        type -= getFightAgentType(type).typeToInt();
        type /= Faction.TYPE_MULTIPLIER;
        
        return Faction.values()[type];
    }
    
    
    public static int getLevel(int type)
    {
        type -= getFightAgentType(type).typeToInt();
        type -= getFaction(type).typeToInt();
        
        return type;
    }
    
    
    @Override
    public Builder<FightAgent> changeBuilder(Builder<FightAgent> builder)
    {
        Builder<FightAgent> oldBuilder = this.builder;
        this.builder = builder;
        return oldBuilder;
    }
    
    
    @Override
    public FightAgent make(int type)
    {
        if (!builder.canBuild(type))
        {
            changeBuilder(getSuitedBuilder(type));
        }
        
        builder.build(type);
        
        
        return builder.result();
    }
    
    
    @Override
    public FightAgent make()
    {
        return make(LEVEL1_MONOCHROME_PAWN_TYPE);
    }
    
    
    @Override
    public int calculateType(FightAgent fightAgent)
    {
        return calculateType(fightAgent.getAllegiance(),fightAgent.getType(), fightAgent.getFaction(), fightAgent.getLevel());
    }
    
    
    @Override
    public Builder<FightAgent> getSuitedBuilder(int type)
    {
        FightAgentType fightAgentType= getFightAgentType(type);
        
        if (fightAgentType == null)
        {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        
        Builder<FightAgent> suitedBuilder;
        
        switch (fightAgentType)
        {
            case KNIGHT:
                suitedBuilder = new KnightBuilder();
                break;
            case BISHOP:
                suitedBuilder = new BishopBuilder();
                break;
            case ROOK:
                suitedBuilder = new RookBuilder();
                break;
            case QUEEN:
                suitedBuilder = new QueenBuilder();
                break;
            case PAWN:
                suitedBuilder = new PawnBuilder();
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
        return suitedBuilder;
    }
}
