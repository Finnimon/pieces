package com.gitgud.pieces.model.gameobjects.agents;

import com.gitgud.engine.model.gameobjects.Leveler;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic.Attack;
import com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic.DamageType;
import com.gitgud.engine.model.gameobjects.agent.attackDefenseLogic.Defence;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;


/**
 * This Class represents a FightAgent in a {@link Fight}.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
public class FightAgent extends Fighter implements Comparable<FightAgent>, Leveler
{
    private final FightAgentType type;
    
    
    private final Faction faction;
    
    
    private final Allegiance allegiance;
    
    
    private final boolean isRangedAttacker;
    
    
    private final int level;
    
    
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
    
    
    public FightAgent(String name, String description, String spriteUrl, boolean isFlying, int movementRange,
                      FightAgentType type, Faction faction, Allegiance allegiance, int level, int meleeDamage, int rangedDamage,
                      int rangedAttackRange, int remainingRangedAttacks, boolean isRangedAttacker, int physicalDefence,
                      int magicDefence, float evadeChance, int maxHealth, int maxMana, int health, int mana,
                      int initiative, float accuracy)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
        this.type = type;
        this.faction = faction;
        this.allegiance = allegiance;
        this.level = level;
        this.meleeDamage = meleeDamage;
        this.rangedDamage = rangedDamage;
        this.rangedAttackRange = rangedAttackRange;
        this.remainingRangedAttacks = remainingRangedAttacks;
        this.isRangedAttacker = isRangedAttacker;
        this.physicalDefence = physicalDefence;
        this.magicDefence = magicDefence;
        this.evadeChance = evadeChance;
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
        this.health = health;
        this.mana = mana;
        this.initiative = initiative;
        this.accuracy = accuracy;
    }
    
    
    public int getMana()
    {
        return mana;
    }
    
    
    public void setMana(int mana)
    {
        this.mana = mana;
    }
    
    
    public float getEvadeChance()
    {
        return evadeChance;
    }
    
    
    public void setEvadeChance(float evadeChance)
    {
        this.evadeChance = evadeChance;
    }
    
    
    public boolean isRangedAttacker()
    {
        return isRangedAttacker;
    }
    
    
    public int getMeleeDamage()
    {
        return meleeDamage;
    }
    
    
    public void setMeleeDamage(int meleeDamage)
    {
        this.meleeDamage = meleeDamage;
    }
    
    
    public int getRangedDamage()
    {
        return rangedDamage;
    }
    
    
    public void setRangedDamage(int rangedDamage)
    {
        this.rangedDamage = rangedDamage;
    }
    
    
    public int getRemainingRangedAttacks()
    {
        return remainingRangedAttacks;
    }
    
    
    public void setRemainingRangedAttacks(int remainingRangedAttacks)
    {
        this.remainingRangedAttacks = remainingRangedAttacks;
    }
    
    
    public int getPhysicalDefence()
    {
        return physicalDefence;
    }
    
    
    public void setPhysicalDefence(int physicalDefence)
    {
        this.physicalDefence = physicalDefence;
    }
    
    
    public int getMagicDefence()
    {
        return magicDefence;
    }
    
    
    public void setMagicDefence(int magicDefence)
    {
        this.magicDefence = magicDefence;
    }
    
    
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    
    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }
    
    
    public int getHealth()
    {
        return health;
    }
    
    
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    
    public int getRangedAttackRange()
    {
        return rangedAttackRange;
    }
    
    
    public void setRangedAttackRange(int rangedAttackRange)
    {
        this.rangedAttackRange = rangedAttackRange;
    }
    
    
    public int getMaxMana()
    {
        return maxMana;
    }
    
    
    public void setMaxMana(int maxMana)
    {
        this.maxMana = maxMana;
    }
    
    
    public int getInitiative()
    {
        return initiative;
    }
    
    
    public void setInitiative(int initative)
    {
        this.initiative = initative;
    }
    
    
    public float getAccuracy()
    {
        return accuracy;
    }
    
    
    public void setAccuracy(float accuracy)
    {
        this.accuracy = accuracy;
    }
    
    
    @Override
    public Defence getDefenceTo(DamageType damageType)
    {
        int defenceValue = damageType != DamageType.MAGIC ? getPhysicalDefence() : getMagicDefence();
        
        
        return new Defence(defenceValue, getEvadeChance(), damageType);
    }
    
    
    @Override
    public Attack createAttack(float distance) throws IllegalArgumentException
    {
        boolean isMelee = Float.isNaN(distance);
        
        if (!isMelee && !canAttackRangedAtDistance(distance))
        {
            throw new IllegalArgumentException("Cannot attack at distance " + distance);
        }
        
        if (!isMelee)
        {
            remainingRangedAttacks--;
        }
        
        int attackValue = isMelee ? getMeleeDamage() : calculateRangedAttackDamage(distance);
        
        
        return new Attack(attackValue, getAccuracy(), DamageType.PHYSICAL);
    }
    
    
    private int calculateRangedAttackDamage(float distance)
    {
        return Math.round(getRangedDamage() * (1 - (distance / 2 / getRangedAttackRange())));
    }
    
    
    private boolean canAttackRangedAtDistance(float distance)
    {
        return getRemainingRangedAttacks() > 0 && getRangedAttackRange() >= Math.round(distance);
    }
    
    
    public Faction getFaction()
    {
        return faction;
    }
    
    
    public FightAgentType getType()
    {
        return type;
    }
    
    
    public int getLevel()
    {
        return level;
    }
    
    
    @Override
    public int levelUp()
    {
        return 0;//todo
    }
    
    
    @Override
    public int compareTo(FightAgent o)
    {
        if (this == o)
        {
            return 0;
        }
        
        
        int comparison = this.getInitiative() - o.getInitiative();
        
        if (comparison != 0)
        {
            return comparison;
        }
        
        
        comparison = this.getLevel() - o.getLevel();
        
        if (comparison != 0)
        {
            return comparison;
        }
        
        
        return this.hashCode() - o.hashCode();
    }
    
    
    public Allegiance getAllegiance()
    {
        return allegiance;
    }
}
