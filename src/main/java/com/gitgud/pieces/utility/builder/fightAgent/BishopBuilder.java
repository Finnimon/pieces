package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;
import com.gitgud.pieces.utility.JsonParser;

import java.io.File;
import java.util.HashSet;


/**
 * Builder for {@link FightAgentType#BISHOP}
 *
 * @author Julius Rohe
 * @Owner: Julius Rohe
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class BishopBuilder extends FightAgentBuilder
{
    
    
    public static final String SPELLS_JSON_FILE_PATH = "src/main/resources/com/gitgud/pieces/utility/builder" +
                                                       "/fightAgent/BishopBuilder/spells.json";
    
    
    private static final String NAME_SUFFIX = " Bishop";
    
    
    private static final String DESCRIPTION = "A Bishop is a strong Mage," +
                                              Strings.LINE_BREAK +
                                              "overcoming his Physical form.";
    
    
    private static final boolean IS_FLYING = false; //
    
    
    private static final int MOVEMENT_RANGE = 3;
    
    
    private static final FightAgentType TYPE = FightAgentType.BISHOP;
    
    
    private static final int MELEE_DAMAGE = 5;
    
    
    private static final int RANGED_DAMAGE = 0;
    
    
    private static final int RANGED_ATTACK_RANGE = 0;
    
    
    private static final int REMAINING_RANGED_ATTACKS = 0;
    
    
    private static final boolean IS_RANGED_ATTACKER = false;
    
    
    private static final int PHYSICAL_DEFENCE = 2;
    
    
    private static final int MAGIC_DEFENCE = 30;
    
    
    private static final float EVADE_CHANCE = 0.2f;
    
    
    private static final int MAX_HEALTH = 70;
    
    
    private static final int MAX_MANA = 300;
    
    
    private static final int INITIATIVE = 80;
    
    
    private static final float ACCURACY = 0.9f;
    
    
    public BishopBuilder()
    {
        super(SpellCasterFightAgent.class);
    }
    
    
    @Override
    public boolean canBuild(int type)
    {
        return FightAgentDirector.getFightAgentType(type) == FightAgentType.BISHOP;
    }
    
    
    @Override
    public void tryBuild(int type)
    {
        var fightAgentType = FightAgentType.BISHOP;
        var allegiance = FightAgentDirector.getAllegiance(type);
        var faction = FightAgentDirector.getFaction(type);
        int level = FightAgentDirector.getLevel(type);
        var testType = FightAgentDirector.calculateType(allegiance, fightAgentType, faction, level);
        
        assert testType == type : "Type mismatch. " + type + " != " + testType;
        
        var name = Named.formatString(faction.name()) + NAME_SUFFIX;
        setName(name);
        
        setDescription(DESCRIPTION);
        
        var spriteFilePath = determineSpriteFilePath(faction, allegiance, fightAgentType);
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
        SpellBook spellBook = new SpellBook(getSpells(faction));
        setSpellBook(spellBook);
    }
    
    
    private HashSet<Spell> getSpells(Faction faction)
    {
        Spell[] spellsArray = JsonParser.getInstance()
                                        .deserializeJsonFile(new File(SPELLS_JSON_FILE_PATH), Spell[].class);
        HashSet<Spell> spells = new HashSet<>();
        spells.add(spellsArray[faction.ordinal()]);
        return spells;
    }
}
