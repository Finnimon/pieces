package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;


/**
 * Builder for {@link FightAgentType#BISHOP}
 *
 * @author Julius Rohe
 * @Owner: Julius Rohe
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class QueenBuilder extends FightAgentBuilder
{
    private static final String NAME_SUFFIX = " Queen";
    
    
    private static final String DESCRIPTION = "A Queen is the STRONGEST off all," +
                                              Strings.LINE_BREAK +
                                              "destroys the Enemies.";
    
    
    private static final boolean IS_FLYING = false; //
    
    
    private static final int MOVEMENT_RANGE = 7;
    
    
    private static final FightAgentType TYPE = FightAgentType.QUEEN;
    
    
    private static final int MELEE_DAMAGE = 25;
    
    
    private static final int RANGED_DAMAGE = 0;
    
    
    private static final int RANGED_ATTACK_RANGE = 0;
    
    
    private static final int REMAINING_RANGED_ATTACKS = 0;
    
    
    private static final boolean IS_RANGED_ATTACKER = false;
    
    
    private static final int PHYSICAL_DEFENCE = 40;
    
    
    private static final int MAGIC_DEFENCE = 25;
    
    
    private static final float EVADE_CHANCE = 0.01f;
    
    
    private static final int MAX_HEALTH = 200;
    
    
    private static final int MAX_MANA = 0;
    
    
    private static final int INITIATIVE = 90;
    
    
    private static final float ACCURACY = 0.7f;
    
    
    public QueenBuilder()
    {
        super(FightAgent.class);
    }
    
    
    @Override
    public boolean canBuild(int type)
    {
        return FightAgentDirector.getFightAgentType(type) == FightAgentType.QUEEN;
    }
    
    
    @Override
    public void tryBuild(int type)
    {
        FightAgentType fightAgentType = FightAgentType.QUEEN;
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
}
