package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


//todo
public class PawnBuilder extends FightAgentBuilder
{
    private static final String NAME_SUFFIX = " Pawn";
    
    
    private static final String DESCRIPTION = "A Pawn is a small figure," +
                                              Strings.LINE_BREAK +
                                              "he is the best at dodging.";
    
    
    private static final boolean IS_FLYING = false; //
    
    
    private static final int MOVEMENT_RANGE = 4;
    
    
    private static final FightAgentType TYPE = FightAgentType.PAWN;
    
    
    private static final int MELEE_DAMAGE = 5;
    
    
    private static final int RANGED_DAMAGE = 0;
    
    
    private static final int RANGED_ATTACK_RANGE = 0;
    
    
    private static final int REMAINING_RANGED_ATTACKS = 0;
    
    
    private static final boolean IS_RANGED_ATTACKER = false;
    
    
    private static final int PHYSICAL_DEFENCE = 10;
    
    
    private static final int MAGIC_DEFENCE = 2;
    
    
    private static final float EVADE_CHANCE = 0.4f;
    
    
    private static final int MAX_HEALTH = 50;
    
    
    private static final int MAX_MANA = 0;
    
    
    private static final int INITIATIVE = 90;
    
    
    private static final float ACCURACY = 0.9f;
    
    
    public PawnBuilder()
    {
        super(FightAgent.class);
    }
    
    
    @Override
    public void tryBuild(int type)
    {
        FightAgentType fightAgentType = FightAgentType.PAWN;
        Allegiance allegiance = FightAgentDirector.getAllegiance(type);
        Faction faction = FightAgentDirector.getFaction(type);
        int level = FightAgentDirector.getLevel(type);
        int testType = FightAgentDirector.calculateType(allegiance, fightAgentType, faction, level);
        
        assert testType == type : "Type mismatch. " + type + " != " + testType;
        
        String name = Named.formatString(faction.name()) + NAME_SUFFIX;
        setName(name);
        
        setDescription(DESCRIPTION);
        
        String spriteFilePath = determineSpriteFilePath(faction, allegiance, fightAgentType);
        setSpriteFilePath(spriteFilePath);
        
        setFaction(faction);
        
        setFlying(IS_FLYING);
        
        setMovementRange(MOVEMENT_RANGE);
        
        setType(fightAgentType);
        
        setMeleeDamage(MELEE_DAMAGE);
        
        setRangedDamage(RANGED_DAMAGE);
        
        setRangedAttackRange(RANGED_ATTACK_RANGE);
        
        setRemainingRangedAttacks(REMAINING_RANGED_ATTACKS);
        
        setRangedAttacker(IS_RANGED_ATTACKER);
        
        setPhysicalDefence(PHYSICAL_DEFENCE);
        
        setMagicDefence(MAGIC_DEFENCE);
        
        setEvadeChance(EVADE_CHANCE);
        
        setMaxHealth(MAX_HEALTH);
        
        setMaxMana(MAX_MANA);
        
        setHealth(MAX_HEALTH);
        
        setMana(MAX_MANA);
        
        setInitiative(INITIATIVE);
        
        setAccuracy(ACCURACY);
        
        setLevel(level);
        
        setAllegiance(allegiance);
    }
    
    
    @Override
    public boolean canBuild(int type)
    {
        return FightAgentDirector.getFightAgentType(type) == FightAgentType.PAWN;
    }
}
