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
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.Fight;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;
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
    
    
    /**
     * <p>Should always be between 0 and 100 exclusive
     * <p>0 means no defence and 100 means invulnerable
     */
    private final SimpleIntegerProperty physicalDefence;
    
    
    /**
     * <p>Should always be between 0 and 100 exclusive
     * <p>0 means no defence and 100 means invulnerable
     */
    private final SimpleIntegerProperty magicDefence;
    
    
    private final SimpleFloatProperty evadeChance;
    
    
    private final SimpleIntegerProperty maxHealth;
    
    
    private final SimpleIntegerProperty maxMana;
    
    
    private final SimpleIntegerProperty health;
    
    
    private final SimpleIntegerProperty mana;
    
    
    private final SimpleIntegerProperty initiative;
    
    
    private final SimpleFloatProperty accuracy;
    
    
    /**
     * Default constructor for {@link FightAgent}. As this is quite long it should not normally be called directly.
     *
     * @param name                   The Name of the {@link FightAgent}
     * @param description            The Description of the {@link FightAgent}
     * @param spriteFilePath         The Path to the Sprite of the {@link FightAgent}
     * @param isFlying               True if the {@link FightAgent} can fly
     * @param movementRange          The Movement Range of the {@link FightAgent}
     * @param type                   The Type of the {@link FightAgent}
     * @param faction                The Faction of the {@link FightAgent}
     * @param allegiance             The Allegiance of the {@link FightAgent}
     * @param level                  The Level of the {@link FightAgent}
     * @param meleeDamage            The melee damage of the {@link FightAgent}
     * @param rangedDamage           The ranged damage of the {@link FightAgent}.
     * @param rangedAttackRange      The ranged attack range of the {@link FightAgent} 0 if the {@link FightAgent} is
     *                              not a ranged attacker
     * @param remainingRangedAttacks The remaining ranged attacks of the {@link FightAgent} 0 if the
     * {@link FightAgent} is not a ranged attacker
     * @param isRangedAttacker       True if the {@link FightAgent} is a ranged attacker
     * @param physicalDefence        The physical defence of the {@link FightAgent}
     * @param magicDefence           The magic defence of the {@link FightAgent}
     * @param evadeChance            The evade chance of the {@link FightAgent}
     * @param maxHealth              The maximum health of the {@link FightAgent}
     * @param maxMana                The maximum mana of the {@link FightAgent}
     * @param health                 The current health of the {@link FightAgent}
     * @param mana                   The current mana of the {@link FightAgent}
     * @param initiative             The initiative of the {@link FightAgent}
     * @param accuracy               The accuracy of the {@link FightAgent}
     * @see FightAgentDirector
     */
    public FightAgent(String name, String description, String spriteFilePath, boolean isFlying, int movementRange,
                      FightAgentType type, Faction faction, Allegiance allegiance, int level, int meleeDamage,
                      int rangedDamage, int rangedAttackRange, int remainingRangedAttacks, boolean isRangedAttacker,
                      int physicalDefence, int magicDefence, float evadeChance, int maxHealth, int maxMana, int health,
                      int mana, int initiative, float accuracy)
    {
        super(name, description, spriteFilePath, isFlying, movementRange);
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
    
    
    /**
     * Getter for {@link #isRangedAttacker} value
     *
     * @return {@link #isRangedAttacker} value
     */
    public boolean isRangedAttacker()
    {
        return isRangedAttacker;
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
    
    
    @Override
    public Defence getDefenceTo(DamageType damageType)
    {
        int defenceValue = (damageType == DamageType.PHYSICAL) ? getPhysicalDefence() : getMagicDefence();
        
        
        return new Defence(defenceValue, getEvadeChance(), damageType);
    }
    
    
    /**
     * Getter for {@link #physicalDefence} value
     *
     * @return {@link #physicalDefence} value
     */
    public int getPhysicalDefence()
    {
        return physicalDefence.get();
    }
    
    
    /**
     * Setter for {@link #physicalDefence} value
     */
    public void setPhysicalDefence(int physicalDefence)
    {
        this.physicalDefence.setValue(physicalDefence);
    }
    
    
    /**
     * Getter for {@link #magicDefence} value
     *
     * @return {@link #magicDefence} value
     */
    public int getMagicDefence()
    {
        return magicDefence.get();
    }
    
    
    /**
     * Getter for the {@link #evadeChance} value
     *
     * @return {@link #evadeChance} value
     */
    public float getEvadeChance()
    {
        return evadeChance.get();
    }
    
    
    /**
     * Setter for {@link #evadeChance} value
     */
    public void setEvadeChance(float evadeChance)
    {
        this.evadeChance.setValue(evadeChance);
    }
    
    
    /**
     * Setter for {@link #magicDefence} value
     */
    public void setMagicDefence(int magicDefence)
    {
        this.magicDefence.setValue(magicDefence);
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
            decrementRemainingRangedAttacks();
        }
        
        
        return new Attack(attackValue, getAccuracy(), DamageType.PHYSICAL);
    }
    
    
    /**
     * <p>Determines whether this {@link FightAgent} can attack at the given distance.
     * <p>Based on {@link #isRangedAttacker}, {@link #getRemainingRangedAttacks} and the {@link #rangedAttackRange}
     *
     * @param distance the distance
     * @return {@code true} if the {@link FightAgent} can attack, {@code false} otherwise
     */
    private boolean canAttackRangedAtDistance(float distance)
    {
        return isRangedAttacker && getRemainingRangedAttacks() > 0 && getRangedAttackRange() >= distance - 0.01f;
    }
    
    
    /**
     * Getter for {@link #meleeDamage} value
     *
     * @return {@link #meleeDamage} value
     */
    public int getMeleeDamage()
    {
        return meleeDamage.get();
    }
    
    
    /**
     * Setter for {@link #meleeDamage} value
     */
    public void setMeleeDamage(int meleeDamage)
    {
        this.meleeDamage.setValue(meleeDamage);
    }
    
    
    /**
     * Calculates the damage for a ranged attack at the given {@param distance}
     *
     * @param distance the distance of the attack
     * @return the calculated damage
     */
    private int calculateRangedAttackDamage(float distance)
    {
        return Math.round(getRangedDamage() * (1 - (distance / 2 / getRangedAttackRange())));
    }
    
    
    /**
     * decrements {@link #remainingRangedAttacks} value
     */
    private void decrementRemainingRangedAttacks()
    {
        setRemainingRangedAttacks(getRemainingRangedAttacks() - 1);
    }
    
    
    @Override
    public SimpleIntegerProperty remainingRangedAttacksProperty()
    {
        return remainingRangedAttacks;
    }
    
    
    /**
     * Getter for {@link #accuracy}
     *
     * @return {@link #accuracy} value
     */
    public float getAccuracy()
    {
        return accuracy.get();
    }
    
    
    /**
     * Getter for {@link #rangedAttackRange}
     *
     * @return {@link #rangedAttackRange} value
     */
    public int getRangedAttackRange()
    {
        return rangedAttackRange.get();
    }
    
    
    /**
     * Getter for {@link #rangedDamage} value
     *
     * @return {@link #meleeDamage} value
     */
    public int getRangedDamage()
    {
        return rangedDamage.get();
    }
    
    
    /**
     * Setter for {@link #rangedDamage} value
     */
    public void setRangedDamage(int rangedDamage)
    {
        this.rangedDamage.setValue(rangedDamage);
    }
    
    
    /**
     * Setter for {@link #rangedAttackRange} value
     */
    public void setRangedAttackRange(int rangedAttackRange)
    {
        this.rangedAttackRange.setValue(rangedAttackRange);
    }
    
    
    /**
     * Setter for {@link #accuracy} value
     */
    public void setAccuracy(float accuracy)
    {
        this.accuracy.setValue(accuracy);
    }
    
    
    /**
     * Getter for {@link #faction}
     *
     * @return {@link #faction}
     */
    public Faction getFaction()
    {
        return faction;
    }
    
    
    /**
     * Getter for the {@link #type}
     *
     * @return {@link #type}
     */
    public FightAgentType getType()
    {
        return type;
    }
    
    
    /**
     * <p>Level up the {@link FightAgent} by 1.
     * <p>The {@link #levelProperty} is increased by 1.
     * <p>All values are adjusted according to the new level as allocated by the {@link FightAgentDirector}.
     *
     * @return the new level
     * @Precondition: The {@link ActiveGameController#getGameState()} must not be
     * {@link com.gitgud.pieces.model.game.GameState#NOT_LOADED}
     * @Postcondition: The method does not throw any exceptions. The Players Artefacts are also reapplied.
     * @see FightAgentDirector
     */
    @Override
    public int levelUp()
    {
        levelProperty.set(getLevel() + 1);
        FightAgentDirector director = new FightAgentDirector();
        int typeInt = director.calculateType(this);
        
        FightAgent nextLevelFightAgent = director.make(typeInt);
        
        setMeleeDamage(nextLevelFightAgent.getMeleeDamage());
        setRangedDamage(nextLevelFightAgent.getRangedDamage());
        setRangedAttackRange(nextLevelFightAgent.getRangedAttackRange());
        setRemainingRangedAttacks(nextLevelFightAgent.getRemainingRangedAttacks());
        setPhysicalDefence(nextLevelFightAgent.getPhysicalDefence());
        setMagicDefence(nextLevelFightAgent.getMagicDefence());
        setEvadeChance(nextLevelFightAgent.getEvadeChance());
        setMaxHealth(nextLevelFightAgent.getMaxHealth());
        setMaxMana(nextLevelFightAgent.getMaxMana());
        setHealth(nextLevelFightAgent.getHealth());
        setMana(nextLevelFightAgent.getMana());
        setInitiative(nextLevelFightAgent.getInitiative());
        setAccuracy(nextLevelFightAgent.getAccuracy());
        
        for (Artefact artefact : ActiveGameController.getInstance()
                                                     .get()
                                                     .getPlayer()
                                                     .artefactPouch()
                                                     .getEquippedArtefacts())
        {
            if (artefact == null)
            {
                continue;
            }
            artefact.getModifier().modify(this);
        }
        
        return getLevel();
    }
    
    
    /**
     * Getter for {@link #maxMana}
     *
     * @return {@link #maxMana} value
     */
    public int getMaxMana()
    {
        return maxMana.get();
    }
    
    
    /**
     * Getter for {@link #mana} value
     *
     * @return {@link #mana} value
     */
    public int getMana()
    {
        return mana.get();
    }
    
    
    /**
     * Setter for {@link #mana}
     */
    public void setMana(int mana)
    {
        this.mana.setValue(mana);
    }
    
    
    /**
     * Getter for {@link #initiative}
     *
     * @return {@link #initiative} value
     */
    public int getInitiative()
    {
        return initiative.get();
    }
    
    
    /**
     * Setter for {@link #initiative} value
     */
    public void setInitiative(int initative)
    {
        this.initiative.setValue(initative);
    }
    
    
    /**
     * Setter for {@link #maxMana} value
     */
    public void setMaxMana(int maxMana)
    {
        this.maxMana.setValue(maxMana);
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
    
    
    /**
     * Filters the {@link Tile}s that are in Attack range by whether an attackable other {@link FightAgent} is on the
     * {@link Tile}
     *
     * @param gridMap         The gridmap on which this {@link FightAgent} is located
     * @param attackableTiles The {@link Tile}s that are in Attack range
     * @return The {@link Tile}s that can be attacked
     */
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
    
    
    /**
     * Getter for {@link #allegiance}
     *
     * @return {@link #allegiance}
     */
    public Allegiance getAllegiance()
    {
        return allegiance;
    }
}
