package com.gitgud.utility.modification;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;


public class FightAgentModifier extends Modifier<FightAgent>
{
    private final int movementRangeModifier;
    
    
    private final int meleeDamageModifier;
    
    
    private final int rangedDamageModifier;
    
    
    private final int rangedAttackRangeModifier;
    
    
    private final int remainingRangedAttacksModifier;
    
    
    private final int physicalDefenceModifier;
    
    
    private final int magicDefenceModifier;
    
    
    private final float evadeChanceModifier;
    
    
    private final int maxHealthModifier;
    
    
    private final int maxManaModifier;
    
    
    private final int healthModifier;
    
    
    private final int manaModifier;
    
    
    private final int initiativeModifier;
    
    
    private final float accuracyModifier;
    
    
    public FightAgentModifier(int movementRangeModifier, int meleeDamageModifier, int rangedDamageModifier,
                              int rangedAttackRangeModifier, int remainingRangedAttacksModifier,
                              int physicalDefenceModifier, int magicDefenceModifier, float evadeChanceModifier,
                              int maxHealthModifier, int maxManaModifier, int healthModifier, int manaModifier,
                              int initiativeModifier, float accuracyModifier)
    {
        this.movementRangeModifier = movementRangeModifier;
        this.meleeDamageModifier = meleeDamageModifier;
        this.rangedDamageModifier = rangedDamageModifier;
        this.rangedAttackRangeModifier = rangedAttackRangeModifier;
        this.remainingRangedAttacksModifier = remainingRangedAttacksModifier;
        this.physicalDefenceModifier = physicalDefenceModifier;
        this.magicDefenceModifier = magicDefenceModifier;
        this.evadeChanceModifier = evadeChanceModifier;
        this.maxHealthModifier = maxHealthModifier;
        this.maxManaModifier = maxManaModifier;
        this.healthModifier = healthModifier;
        this.manaModifier = manaModifier;
        this.initiativeModifier = initiativeModifier;
        this.accuracyModifier = accuracyModifier;
    }
    
    
    @Override
    public FightAgent modify(FightAgent fightFigure)
    {
        int rangedAttackRange = fightFigure.getRangedAttackRange();
        int rangedDamage = fightFigure.getRangedDamage();
        int remainingRangedAttacks = fightFigure.getRemainingRangedAttacks();
        if (fightFigure.isRangedAttacker())
        {
            rangedAttackRange += rangedAttackRangeModifier;
            rangedDamage += rangedDamageModifier;
            remainingRangedAttacks += remainingRangedAttacksModifier;
        }
        
        int movementRange = fightFigure.getMovementRange() + movementRangeModifier;
        int meleeDamage = fightFigure.getMeleeDamage() + meleeDamageModifier;
        int physicalDefence = fightFigure.getPhysicalDefence() + physicalDefenceModifier;
        int magicDefence = fightFigure.getMagicDefence() + magicDefenceModifier;
        float evadeChance = fightFigure.getEvadeChance() * evadeChanceModifier;
        int maxHealth = fightFigure.getMaxHealth() + maxHealthModifier;
        int maxMana = fightFigure.getMaxMana() + maxManaModifier;
        int health = fightFigure.getHealth() + healthModifier;
        int mana = fightFigure.getMana() + manaModifier;
        int initiative = fightFigure.getInitiative() + initiativeModifier;
        float accuracy = fightFigure.getAccuracy() * accuracyModifier;
        
        if (health > maxHealth)
        {
            health = maxHealth;
        }
        
        if (mana > maxMana)
        {
            mana = maxMana;
        }
        
        
        return new FightAgent(fightFigure.getName(), fightFigure.getDescription(), fightFigure.getSpriteUrl(),
                              fightFigure.isFlying(), movementRange, fightFigure.getType(), fightFigure.getFaction(),
                              meleeDamage, rangedDamage, rangedAttackRange, remainingRangedAttacks, physicalDefence,
                              magicDefence, evadeChance, maxHealth, maxMana, health, mana, initiative, accuracy);
    }
    
    
    @Override
    public FightAgent demodify(FightAgent fightFigure)
    {
        int rangedAttackRange = fightFigure.getRangedAttackRange();
        int rangedDamage = fightFigure.getRangedDamage();
        int remainingRangedAttacks = fightFigure.getRemainingRangedAttacks();
        if (fightFigure.isRangedAttacker())
        {
            rangedAttackRange -= rangedAttackRangeModifier;
            rangedDamage -= rangedDamageModifier;
            remainingRangedAttacks -= remainingRangedAttacksModifier;
        }
        
        int movementRange = fightFigure.getMovementRange() - movementRangeModifier;
        int meleeDamage = fightFigure.getMeleeDamage() - meleeDamageModifier;
        int physicalDefence = fightFigure.getPhysicalDefence() - physicalDefenceModifier;
        int magicDefence = fightFigure.getMagicDefence() - magicDefenceModifier;
        float evadeChance = fightFigure.getEvadeChance() / evadeChanceModifier;
        int maxHealth = fightFigure.getMaxHealth() - maxHealthModifier;
        int maxMana = fightFigure.getMaxMana() - maxManaModifier;
        int health = fightFigure.getHealth() - healthModifier;
        int mana = fightFigure.getMana() - manaModifier;
        int initiative = fightFigure.getInitiative() - initiativeModifier;
        float accuracy = fightFigure.getAccuracy() / accuracyModifier;
        
        if (health > maxHealth)
        {
            health = maxHealth;
        }
        
        if (mana > maxMana)
        {
            mana = maxMana;
        }
        
        
        return new FightAgent(fightFigure.getName(), fightFigure.getDescription(), fightFigure.getSpriteUrl(),
                              fightFigure.isFlying(), movementRange, fightFigure.getType(), fightFigure.getFaction(),
                              meleeDamage, rangedDamage, rangedAttackRange, remainingRangedAttacks, physicalDefence,
                              magicDefence, evadeChance, maxHealth, maxMana, health, mana, initiative, accuracy);
    }
}
