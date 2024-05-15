package com.gitgud.utility.modification;

import com.gitgud.model.gameObjects.gridMovable.FightAgent;


public class FightAgentModifier extends Modifier<FightAgent>
{
    private final int movementRangeModifier;
    
    
    private final int rankModifier;
    
    
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
    
    
    public FightAgentModifier(int movementRangeModifier, int rankModifier, int meleeDamageModifier, int rangedDamageModifier,
                              int rangedAttackRangeModifier, int remainingRangedAttacksModifier,
                              int physicalDefenceModifier, int magicDefenceModifier, float evadeChanceModifier,
                              int maxHealthModifier, int maxManaModifier, int healthModifier, int manaModifier,
                              int initiativeModifier, float accuracyModifier)
    {
        this.movementRangeModifier = movementRangeModifier;
        this.rankModifier = rankModifier;
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
    public FightAgent modify(FightAgent fightAgent)
    {
        int rank=fightAgent.getRank()+rankModifier;
        int rangedAttackRange = fightAgent.getRangedAttackRange();
        int rangedDamage = fightAgent.getRangedDamage();
        int remainingRangedAttacks = fightAgent.getRemainingRangedAttacks();
        if (fightAgent.isRangedAttacker())
        {
            rangedAttackRange += rangedAttackRangeModifier;
            rangedDamage += rangedDamageModifier;
            remainingRangedAttacks += remainingRangedAttacksModifier;
        }
        
        int movementRange = fightAgent.getMovementRange() + movementRangeModifier;
        int meleeDamage = fightAgent.getMeleeDamage() + meleeDamageModifier;
        int physicalDefence = fightAgent.getPhysicalDefence() + physicalDefenceModifier;
        int magicDefence = fightAgent.getMagicDefence() + magicDefenceModifier;
        float evadeChance = fightAgent.getEvadeChance() * evadeChanceModifier;
        int maxHealth = fightAgent.getMaxHealth() + maxHealthModifier;
        int maxMana = fightAgent.getMaxMana() + maxManaModifier;
        int health = fightAgent.getHealth() + healthModifier;
        int mana = fightAgent.getMana() + manaModifier;
        int initiative = fightAgent.getInitiative() + initiativeModifier;
        float accuracy = fightAgent.getAccuracy() * accuracyModifier;
        
        if (health > maxHealth)
        {
            health = maxHealth;
        }
        
        if (mana > maxMana)
        {
            mana = maxMana;
        }
        
        
        return new FightAgent(fightAgent.getName(), fightAgent.getDescription(), fightAgent.getSpriteUrl(),
                              fightAgent.isFlying(), movementRange, fightAgent.getType(), fightAgent.getFaction(),
                              rank, meleeDamage, rangedDamage, rangedAttackRange, remainingRangedAttacks, physicalDefence,
                              magicDefence, evadeChance, maxHealth, maxMana, health, mana, initiative, accuracy);
    }
    
    
    @Override
    public FightAgent demodify(FightAgent fightAgent)
    {
        int rank=fightAgent.getRank()-rankModifier;
        int rangedAttackRange = fightAgent.getRangedAttackRange();
        int rangedDamage = fightAgent.getRangedDamage();
        int remainingRangedAttacks = fightAgent.getRemainingRangedAttacks();
        if (fightAgent.isRangedAttacker())
        {
            rangedAttackRange -= rangedAttackRangeModifier;
            rangedDamage -= rangedDamageModifier;
            remainingRangedAttacks -= remainingRangedAttacksModifier;
        }
        
        int movementRange = fightAgent.getMovementRange() - movementRangeModifier;
        int meleeDamage = fightAgent.getMeleeDamage() - meleeDamageModifier;
        int physicalDefence = fightAgent.getPhysicalDefence() - physicalDefenceModifier;
        int magicDefence = fightAgent.getMagicDefence() - magicDefenceModifier;
        float evadeChance = fightAgent.getEvadeChance() / evadeChanceModifier;
        int maxHealth = fightAgent.getMaxHealth() - maxHealthModifier;
        int maxMana = fightAgent.getMaxMana() - maxManaModifier;
        int health = fightAgent.getHealth() - healthModifier;
        int mana = fightAgent.getMana() - manaModifier;
        int initiative = fightAgent.getInitiative() - initiativeModifier;
        float accuracy = fightAgent.getAccuracy() / accuracyModifier;
        
        if (health > maxHealth)
        {
            health = maxHealth;
        }
        
        if (mana > maxMana)
        {
            mana = maxMana;
        }
        
        
        return new FightAgent(fightAgent.getName(), fightAgent.getDescription(), fightAgent.getSpriteUrl(),
                              fightAgent.isFlying(), movementRange, fightAgent.getType(), fightAgent.getFaction(),
                              rank, meleeDamage, rangedDamage, rangedAttackRange, remainingRangedAttacks, physicalDefence,
                              magicDefence, evadeChance, maxHealth, maxMana, health, mana, initiative, accuracy);
    }
}
