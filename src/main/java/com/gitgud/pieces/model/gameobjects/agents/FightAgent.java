package com.gitgud.pieces.model.gameobjects.agents;

import com.gitgud.engine.model.attackDefenseLogic.Attack;
import com.gitgud.engine.model.attackDefenseLogic.DamageType;
import com.gitgud.engine.model.attackDefenseLogic.Defence;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.GridMovable;
import com.gitgud.engine.model.gameobjects.Leveler;
import com.gitgud.engine.model.gameobjects.agent.Fighter;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;
import com.github.ruediste.polymorphicGson.GsonPolymorph;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


/**
 * This Class represents a FightAgent in a {@link Fight}.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.04.2024
 * @Version: 1.0
 */
@GsonPolymorph
public class FightAgent extends Fighter implements Comparable<FightAgent>, Leveler
{
    private final FightAgentType type;
    
    
    private final Faction faction;
    
    
    private final Allegiance allegiance;
    
    
    private final boolean isRangedAttacker;
    
    
    private final SimpleIntegerProperty levelProperty;
    
    
    private final SimpleIntegerProperty meleeDamage;
    
    
    private final SimpleIntegerProperty rangedDamage;
    
    
    private final SimpleIntegerProperty rangedAttackRange;
    
    
    private final SimpleIntegerProperty remainingRangedAttacks;
    
    
    private final SimpleIntegerProperty physicalDefence;
    
    
    private final SimpleIntegerProperty magicDefence;
    
    
    private final SimpleFloatProperty evadeChance;
    
    
    private final SimpleIntegerProperty maxHealth;
    
    
    private final SimpleIntegerProperty maxMana;
    
    
    private final SimpleIntegerProperty health;
    
    
    private final SimpleIntegerProperty mana;
    
    
    private final SimpleIntegerProperty initiative;
    
    
    private final SimpleFloatProperty accuracy;
    
    
    public FightAgent(String name, String description, String spriteUrl, boolean isFlying, int movementRange,
                      FightAgentType type, Faction faction, Allegiance allegiance, int level, int meleeDamage,
                      int rangedDamage, int rangedAttackRange, int remainingRangedAttacks, boolean isRangedAttacker,
                      int physicalDefence, int magicDefence, float evadeChance, int maxHealth, int maxMana, int health,
                      int mana, int initiative, float accuracy)
    {
        super(name, description, spriteUrl, isFlying, movementRange);
        this.type = type;
        this.faction = faction;
        this.allegiance = allegiance;
        this.levelProperty = new SimpleIntegerProperty(level);
        this.meleeDamage = new SimpleIntegerProperty(meleeDamage);
        this.rangedDamage = new SimpleIntegerProperty(rangedDamage);
        this.rangedAttackRange = new SimpleIntegerProperty(rangedAttackRange);
        this.remainingRangedAttacks = new SimpleIntegerProperty(remainingRangedAttacks);
        this.isRangedAttacker = isRangedAttacker;
        this.physicalDefence = new SimpleIntegerProperty(physicalDefence);
        this.magicDefence = new SimpleIntegerProperty(magicDefence);
        this.evadeChance = new SimpleFloatProperty(evadeChance);
        this.maxHealth = new SimpleIntegerProperty(maxHealth);
        this.maxMana = new SimpleIntegerProperty(maxMana);
        this.health = new SimpleIntegerProperty(health);
        this.mana = new SimpleIntegerProperty(mana);
        this.initiative = new SimpleIntegerProperty(initiative);
        this.accuracy = new SimpleFloatProperty(accuracy);
    }
    
    
    public int getMana()
    {
        return mana.get();
    }
    
    
    public void setMana(int mana)
    {
        this.mana.setValue(mana);
    }
    
    
    public float getEvadeChance()
    {
        return evadeChance.get();
    }
    
    
    public void setEvadeChance(float evadeChance)
    {
        this.evadeChance.setValue(evadeChance);
    }
    
    
    public boolean isRangedAttacker()
    {
        return isRangedAttacker;
    }
    
    
    public int getMeleeDamage()
    {
        return meleeDamage.get();
    }
    
    
    public void setMeleeDamage(int meleeDamage)
    {
        this.meleeDamage.setValue(meleeDamage);
    }
    
    
    public int getRangedDamage()
    {
        return rangedDamage.get();
    }
    
    
    public void setRangedDamage(int rangedDamage)
    {
        this.rangedDamage.setValue(rangedDamage);
    }
    
    
    public int getRemainingRangedAttacks()
    {
        return remainingRangedAttacks.get();
    }
    
    
    public void setRemainingRangedAttacks(int remainingRangedAttacks)
    {
        this.remainingRangedAttacks.setValue(remainingRangedAttacks);
    }
    
    
    public int getPhysicalDefence()
    {
        return physicalDefence.get();
    }
    
    
    public void setPhysicalDefence(int physicalDefence)
    {
        this.physicalDefence.setValue(physicalDefence);
    }
    
    
    public int getMagicDefence()
    {
        return magicDefence.get();
    }
    
    
    public void setMagicDefence(int magicDefence)
    {
        this.magicDefence.setValue(magicDefence);
    }
    
    
    public int getMaxHealth()
    {
        return maxHealth.get();
    }
    
    
    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth.setValue(maxHealth);
    }
    
    
    @Override
    public SimpleIntegerProperty healthProperty()
    {
        return health;
    }
    
    
    @Override
    public SimpleIntegerProperty maxHealthProperty()
    {
        return maxHealth;
    }
    
    
    public int getHealth()
    {
        return health.get();
    }
    
    
    public void setHealth(int health)
    {
        this.health.setValue(health);
    }
    
    
    public int getRangedAttackRange()
    {
        return rangedAttackRange.get();
    }
    
    
    public void setRangedAttackRange(int rangedAttackRange)
    {
        this.rangedAttackRange.setValue(rangedAttackRange);
    }
    
    
    public int getMaxMana()
    {
        return maxMana.get();
    }
    
    
    public void setMaxMana(int maxMana)
    {
        this.maxMana.setValue(maxMana);
    }
    
    
    public int getInitiative()
    {
        return initiative.get();
    }
    
    
    public void setInitiative(int initative)
    {
        this.initiative.setValue(initative);
    }
    
    
    public float getAccuracy()
    {
        return accuracy.get();
    }
    
    
    public void setAccuracy(float accuracy)
    {
        this.accuracy.setValue(accuracy);
    }
    
    
    @Override
    public Defence getDefenceTo(DamageType damageType)
    {
        int defenceValue = (damageType == DamageType.PHYSICAL) ? getPhysicalDefence() : getMagicDefence();
        
        
        return new Defence(defenceValue, getEvadeChance(), damageType);
    }
    
    
    @Override
    public Attack createAttack(float distance) throws IllegalArgumentException
    {
        boolean isMelee = Float.isNaN(distance) || distance <= Math.sqrt(2) + 0.01;
        
        if (!isMelee && !canAttackRangedAtDistance(distance))
        {
            throw new IllegalArgumentException("Cannot attack at distance " + distance);
        }
        
        
        int attackValue = isMelee ? getMeleeDamage() : calculateRangedAttackDamage(distance);
        if (!isMelee)
        {
            setRemainingRangedAttacks(getRemainingRangedAttacks() - 1);
        }
        
        
        return new Attack(attackValue, getAccuracy(), DamageType.PHYSICAL);
    }
    
    
    public boolean canAttackAtDistance(float distance)
    {
        float floatingPointNumberComparisonAdjuster = 0.01f;
        distance -= floatingPointNumberComparisonAdjuster;
        
        boolean attackIsMelee = distance <= Math.sqrt(2) || Float.isNaN(distance);
        
        if (attackIsMelee)
        {
            return true;
        }
        
        boolean isRangedAttacker = this.isRangedAttacker;
        
        return isRangedAttacker && canAttackRangedAtDistance(distance);
        
    }
    
    
    private int calculateRangedAttackDamage(float distance)
    {
        return Math.round(getRangedDamage() * (1 - (distance / 2 / getRangedAttackRange())));
    }
    
    
    private boolean canAttackRangedAtDistance(float distance)
    {
        return getRemainingRangedAttacks() > 0 && getRangedAttackRange() >= distance - 0.01f;
    }
    
    
    public Faction getFaction()
    {
        return faction;
    }
    
    
    public FightAgentType getType()
    {
        return type;
    }
    
   
    
    @Override
    public int levelUp()
    {
        levelProperty.set(getLevel() + 1);
        FightAgentDirector director = new FightAgentDirector();
        int typeInt = director.calculateType(this);
        
        FightAgent nextLevelFightAgent = director.make(typeInt);
        //thjis copy vals
        
        return getLevel();//todo
    }
    
    
    @Override
    public SimpleIntegerProperty levelProperty()
    {
        return levelProperty;
    }
    
    
    @Override
    public int compareTo(FightAgent o)
    {
        if (this == o)
        {
            return 0;
        }
        
        
        int comparison = o.getInitiative() - this.getInitiative();
        
        if (comparison != 0)
        {
            return comparison;
        }
        
        
        comparison = o.getLevel() - this.getLevel();
        
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
    
    
    @Override
    public Collection<Tile> findAttackableTiles(GridMap gridMap, Tile position)
    {
        HashSet<Tile> attackableTiles = new HashSet<Tile>(gridMap.getNeighbors(position));
        
        boolean canAttackRanged = isRangedAttacker && getRemainingRangedAttacks() > 0;
        if (canAttackRanged)
        
        {
            attackableTiles.addAll(GridMovable.getInAbsoluteRangeTiles(gridMap, position, getRangedAttackRange()));
        }
        
        
        return filterAttackableTiles(gridMap, attackableTiles);
        
    }
    
    
    private Collection<Tile> filterAttackableTiles(GridMap<GridMappable> gridMap, Collection<Tile> attackableTiles)
    {
        Iterator<Tile> iterator = attackableTiles.iterator();
        iterator.forEachRemaining(tile ->
                                  {
                                      if (tile == null)
                                      {
                                          iterator.remove();
                                          return;
                                      }
                                      
                                      GridMappable gridMappable = gridMap.get(tile);
                                      
                                      if (gridMappable == null)
                                      {
                                          iterator.remove();
                                          return;
                                      }
                                      
                                      if (!(gridMappable instanceof FightAgent other))
                                      {
                                          iterator.remove();
                                          return;
                                      }
                                      
                                      if (other.getAllegiance() == allegiance)
                                      {
                                          iterator.remove();
                                      }
                                  });
        return attackableTiles;
    }
}
