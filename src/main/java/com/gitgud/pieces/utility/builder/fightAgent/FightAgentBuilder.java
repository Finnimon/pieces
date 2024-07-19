package com.gitgud.pieces.utility.builder.fightAgent;

import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.SpellCasterFightAgent;
import com.gitgud.pieces.utility.builder.Builder;


public abstract class FightAgentBuilder implements Builder<FightAgent>
{
    protected static final String SPRITE_FILEPATH_DIRECTORY = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\agents\\";
    
    
    private final Class<? extends FightAgent> typeClass;
    
    
    private String name;
    
    
    private String description;
    
    
    private String spriteFilePath;
    
    
    private boolean isFlying;
    
    
    private FightAgentType type;
    
    
    private Faction faction;
    
    
    private boolean isRangedAttacker;
    
    
    private int level;
    
    
    private int meleeDamage;
    
    
    private int rangedDamage;
    
    
    private int rangedAttackRange;
    
    
    private int remainingRangedAttacks;
    
    
    private int physicalDefence;
    
    
    private int magicDefence;
    
    
    private float evadeChance;
    
    
    private int maxHealth;
    
    
    private int maxMana;
    
    
    private int health;
    
    
    private int mana;
    
    
    private int initiative;
    
    
    private float accuracy;
    
    
    private int movementRange;
    
    
    private SpellBook spellBook;
    
    
    private Allegiance allegiance;
    
    
    protected FightAgentBuilder(Class<? extends FightAgent> typeClass)
    {
        this.typeClass = typeClass;
        validateTypeClass();
    }
    
    
    protected static String determineSpriteFilePath(Faction faction, Allegiance allegiance,
                                                    FightAgentType fightAgentType)
    {
        return "%s%s%s%s_%s%s".formatted(SPRITE_FILEPATH_DIRECTORY, faction.name().toLowerCase(),
                                         Strings.FILE_SEPERATOR, allegiance, fightAgentType.name(), Sprite.DOT_PNG);
    }
    
    
    private static boolean isKnownTypeClass(Class<? extends FightAgent> typeClass)
    {
        return typeClass == SpellCasterFightAgent.class || typeClass == FightAgent.class;
    }
    
    
    public Allegiance getAllegiance()
    {
        return allegiance;
    }
    
    
    public FightAgentBuilder setAllegiance(Allegiance allegiance)
    {
        this.allegiance = allegiance;
        return this;
    }
    
    
    private void validateTypeClass()
    {
        if (isKnownTypeClass(typeClass))
        {
            return;
        }
        
        throw new IllegalStateException("Unknown type: " + typeClass);
    }
    
    
    public String getName()
    {
        return name;
    }
    
    
    public FightAgentBuilder setName(String name)
    {
        this.name = name;
        return this;
    }
    
    
    public String getDescription()
    {
        return description;
    }
    
    
    public FightAgentBuilder setDescription(String description)
    {
        this.description = description;
        return this;
    }
    
    
    public String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
    
    public FightAgentBuilder setSpriteFilePath(String spriteFilePath)
    {
        this.spriteFilePath = spriteFilePath;
        return this;
    }
    
    
    public boolean isFlying()
    {
        return isFlying;
    }
    
    
    public FightAgentBuilder setFlying(boolean flying)
    {
        isFlying = flying;
        return this;
    }
    
    
    public FightAgentType getType()
    {
        return type;
    }
    
    
    public FightAgentBuilder setType(FightAgentType type)
    {
        this.type = type;
        return this;
    }
    
    
    public Faction getFaction()
    {
        return faction;
    }
    
    
    public FightAgentBuilder setFaction(Faction faction)
    {
        this.faction = faction;
        return this;
    }
    
    
    public boolean isRangedAttacker()
    {
        return isRangedAttacker;
    }
    
    
    public FightAgentBuilder setRangedAttacker(boolean rangedAttacker)
    {
        isRangedAttacker = rangedAttacker;
        return this;
    }
    
    
    public int getLevel()
    {
        return level;
    }
    
    
    public FightAgentBuilder setLevel(int level)
    {
        this.level = level;
        return this;
    }
    
    
    public int getMeleeDamage()
    {
        return meleeDamage;
    }
    
    
    public FightAgentBuilder setMeleeDamage(int meleeDamage)
    {
        this.meleeDamage = meleeDamage;
        return this;
    }
    
    
    public int getRangedDamage()
    {
        return rangedDamage;
    }
    
    
    public FightAgentBuilder setRangedDamage(int rangedDamage)
    {
        this.rangedDamage = rangedDamage;
        return this;
    }
    
    
    public int getRangedAttackRange()
    {
        return rangedAttackRange;
    }
    
    
    public FightAgentBuilder setRangedAttackRange(int rangedAttackRange)
    {
        this.rangedAttackRange = rangedAttackRange;
        return this;
    }
    
    
    public int getRemainingRangedAttacks()
    {
        return remainingRangedAttacks;
    }
    
    
    public FightAgentBuilder setRemainingRangedAttacks(int remainingRangedAttacks)
    {
        this.remainingRangedAttacks = remainingRangedAttacks;
        return this;
    }
    
    
    public int getPhysicalDefence()
    {
        return physicalDefence;
    }
    
    
    public FightAgentBuilder setPhysicalDefence(int physicalDefence)
    {
        this.physicalDefence = physicalDefence;
        return this;
    }
    
    
    public int getMagicDefence()
    {
        return magicDefence;
    }
    
    
    public FightAgentBuilder setMagicDefence(int magicDefence)
    {
        this.magicDefence = magicDefence;
        return this;
    }
    
    
    public float getEvadeChance()
    {
        return evadeChance;
    }
    
    
    public FightAgentBuilder setEvadeChance(float evadeChance)
    {
        this.evadeChance = evadeChance;
        return this;
    }
    
    
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    
    public FightAgentBuilder setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
        return this;
    }
    
    
    public int getMaxMana()
    {
        return maxMana;
    }
    
    
    public FightAgentBuilder setMaxMana(int maxMana)
    {
        this.maxMana = maxMana;
        return this;
    }
    
    
    public int getHealth()
    {
        return health;
    }
    
    
    public FightAgentBuilder setHealth(int health)
    {
        this.health = health;
        return this;
    }
    
    
    public int getMana()
    {
        return mana;
    }
    
    
    public FightAgentBuilder setMana(int mana)
    {
        this.mana = mana;
        return this;
    }
    
    
    public int getInitiative()
    {
        return initiative;
    }
    
    
    public FightAgentBuilder setInitiative(int initiative)
    {
        this.initiative = initiative;
        return this;
    }
    
    
    public float getAccuracy()
    {
        return accuracy;
    }
    
    
    public FightAgentBuilder setAccuracy(float accuracy)
    {
        this.accuracy = accuracy;
        return this;
    }
    
    
    public int getMovementRange()
    {
        return movementRange;
    }
    
    
    public FightAgentBuilder setMovementRange(int movementRange)
    {
        this.movementRange = movementRange;
        return this;
    }
    
    
    public SpellBook getSpellBook()
    {
        return spellBook;
    }
    
    
    public FightAgentBuilder setSpellBook(SpellBook spellBook)
    {
        this.spellBook = spellBook;
        return this;
    }
    
    
    public FightAgent result()
    {
        adjustValuesByFaction();
        adjustValuesByLevel();
        
        if (typeClass == SpellCasterFightAgent.class)
        {
            return getSpellCasterFightAgentResult();
        }
        if (typeClass == FightAgent.class)
        {
            return getFightAgentResult();
        }
        
        throw new IllegalStateException("Unknown type: " + typeClass);
    }
    
    
    private FightAgent getFightAgentResult()
    {
        return new FightAgent(name, description, spriteFilePath, isFlying, movementRange, type, faction, allegiance,
                              level, meleeDamage, rangedDamage, rangedAttackRange, remainingRangedAttacks,
                              isRangedAttacker, physicalDefence, magicDefence, evadeChance, maxHealth, maxMana, health,
                              mana, initiative, accuracy);
    }
    
    
    private SpellCasterFightAgent getSpellCasterFightAgentResult()
    {
        return new SpellCasterFightAgent(name, description, spriteFilePath, isFlying, movementRange, type, faction,
                                         allegiance, level, meleeDamage, rangedDamage, rangedAttackRange,
                                         remainingRangedAttacks, isRangedAttacker, physicalDefence, magicDefence,
                                         evadeChance, maxHealth, maxMana, health, mana, initiative, accuracy,
                                         spellBook);
    }
    
    
    @Override
    public void reset()
    {
        name = null;
        description = null;
        spriteFilePath = null;
        isFlying = false;
        movementRange = 0;
        type = null;
        faction = null;
        level = 0;
        meleeDamage = 0;
        rangedDamage = 0;
        rangedAttackRange = 0;
        remainingRangedAttacks = 0;
        isRangedAttacker = false;
        physicalDefence = 0;
        magicDefence = 0;
        evadeChance = 0;
        maxHealth = 0;
        maxMana = 0;
        health = 0;
        mana = 0;
        initiative = 0;
        accuracy = 0;
        spellBook = null;
    }
    
    
    protected void adjustValuesByFaction()
    {
        float damageModifier;
        switch (faction)
        {
            case PINK:
                damageModifier = 1.5f;
                //Make them a tiny bit faster to really profit from higher attack lower armor etc
                initiative = Math.round(initiative * 1.1f);
                break;
            case GREEN:
                damageModifier = 0.9f;
                break;
            default:
                damageModifier = 1.0f;
                //            case MONOCHROME:
                //Explicitly do not change as this is the default conifguration/faction
                break;
        }
        
        applyDamageScalar(damageModifier);
    }
    
    
    private void applyDamageScalar(float damageModifier)
    {
        meleeDamage = Math.round(meleeDamage * damageModifier);
        rangedDamage = Math.round(rangedDamage * damageModifier);
        
        accuracy = accuracy * damageModifier;
        evadeChance /= damageModifier;
        
        physicalDefence = Math.round(physicalDefence / damageModifier);
        magicDefence = Math.round(magicDefence * damageModifier);
        
        maxHealth = Math.round(maxHealth * damageModifier);
        health = maxHealth;
    }
    
    
    protected void adjustValuesByLevel()
    {
        float scalar = level;
        
        
        scaleValues(scalar);
        levelUpSpellsIfNeeded();
    }
    
    
    private void scaleValues(float scalar)
    {
        maxHealth = Math.round(maxHealth * scalar);
        health = maxHealth;
        
        maxMana = Math.round(maxMana * scalar);
        mana = maxMana;
        
        meleeDamage = Math.round(meleeDamage * scalar);
        rangedDamage = Math.round(rangedDamage * scalar);
        
        physicalDefence = Math.round(physicalDefence);
        magicDefence = Math.round(magicDefence);
        
        initiative = Math.round(initiative * scalar);
    }
    
    
    private void levelUpSpellsIfNeeded()
    {
        if (spellBook == null)
        {
            return;
        }
        for (Spell spell : spellBook.spells())
        {
            while (spell.getLevel() < level)
            {
                spell.levelUp();
            }
        }
    }
}
