package com.gitgud.model.gameObjects;

/**
 * @author Finn Kramer
 * @version 1.0
 */
public interface GameObjectAttribute
{
    // class GameObject
    String NAME = "name";
    String DESCRIPTION = "description";
    String SPRITE_URL = "spriteUrl";

    // class Agent
    String IS_FLYING = "isFlying";
    String MOVEMENT_RANGE = "movementRange";

    // class FightAgent
    String SPRITE_URL_WHITE = "spriteUrlWhite";
    String SPRITE_URL_BLACK = "spriteUrlBlack";
    String IS_RANGED_ATTACKER = "isRangedAttacker";
    String RANK = "rank";
    String TYPE = "type";
    String HEALTH = "health";
    String MAX_HEALTH = "maxHealth";
    String PHYSICAL_DEFENCE = "physicalDefence";
    String MANA = "mana";
    String MAX_MANA = "maxMana";
    String MELEE_DAMAGE = "meleeDamage";
    String RANGE_DAMAGE = "rangeDamage";
    String REMAINING_RANGED_ATTACKS = "remainingRangedAttacks";
    String EVADE_CHANCE = "evadeChance";
    String MAGIC_DEFENCE = "magicDefence";
    String RANGED_ATTACK_RANGE = "rangedAttackRange";
    String INITIATIVE = "initiative";
    String ACCURACY = "accuracy";

    // class Player
    String FOOD = "food";
    String WATER = "water";
    String SAND = "sand";
    String WOOD = "wood";
    String ORE = "ore";
    String SILVER = "silver";
    String GOLD = "gold";


    // class Spell
    String COST = "cost";
    String HEALTH_BOOST = "healthBoost";
    String SHIELD_BOOST = "shieldBoost";
    String POISON = "poison";

}
