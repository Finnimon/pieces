package com.gitgud.model.gameObjects.gridMovable;

import com.gitgud.model.fight.*;
import com.gitgud.model.gameObjects.AssetLocator;
import com.gitgud.model.gameObjects.Faction;
import com.gitgud.model.gameObjects.FightAgentType;
import com.gitgud.model.gameObjects.GameObjectAttribute;
import com.gitgud.utility.ExceptionMessage;
import com.gitgud.utility.services.AssetParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author Finn Maximilian Kramer
 * @version 1.0
 */
public class FightAgent extends Agent implements Defender, Attacker
{
    // Typ des FightAgent-Objekts
    private final String type;

    // Fraktion des FightAgent-Objekts
    private final String faction;

    // Rang des FightAgent-Objekts
    private int rank;

    // Kampf-Attribute des FightAgent-Objekts
    private HashMap<String, Integer> fightAttributes;


    /**
     * Die Konstruktor-Methode stellt ein FightAgent-Objekt für entsprechenden Typ, Rang, Fraktion & Farbe bereit.
     * @param type Typ des FightAgent-Objekts
     * @param faction Fraktion des FightAgent-Objekts
     * @param rank Rang des FightAgent-Objekts
     * @param color Farbe des FightAgent-Objekts
     */
    public FightAgent (String type, String faction, int rank, String color)
    {
        this.type = type;
        this.faction = faction;
        this.rank = rank;
        init(type, color);
    }


    /**
     * Initialisiert ein FightAgent-Objekt entsprechend dem Typ.
     * @param type Typ des FightAgent-Objekts
     */
    private void init (String type, String color)
    {
        JsonArray types = AssetParser.parseJsonArray(AssetLocator.FIGHT_AGENT_TYPES);
        JsonObject fightAttributes = AssetParser.getFightAgentByType(types, type);

        this.setName(fightAttributes.get(GameObjectAttribute.TYPE).getAsString());
        this.setDescription(fightAttributes.get(GameObjectAttribute.DESCRIPTION).getAsString());

        switch (color)
        {
            case ("white"):
                this.setSpriteUrl(fightAttributes.get(GameObjectAttribute.SPRITE_URL_WHITE).getAsString());
                break;

            case ("black"):
                this.setSpriteUrl(fightAttributes.get(GameObjectAttribute.SPRITE_URL_BLACK).getAsString());
                break;

            default:
                break;
        }

        this.fightAttributes = AssetParser.mapAttributeKeysToIntegers(fightAttributes);
    }

    @Override
    public boolean isFlying ()
    {
        return Objects.requireNonNull(AssetParser.getAttributeValueByTypeFromArray(AssetLocator.FIGHT_AGENT_TYPES,
                this.type, GameObjectAttribute.IS_FLYING)).getAsBoolean();
    }

    public boolean isRangedAttacker ()
    {
        return Objects.requireNonNull(AssetParser.getAttributeValueByTypeFromArray(AssetLocator.FIGHT_AGENT_TYPES,
                this.type, GameObjectAttribute.IS_RANGED_ATTACKER)).getAsBoolean();
    }

    @Override
    public int getMovementRange ()
    {
        return this.fightAttributes.get(GameObjectAttribute.MOVEMENT_RANGE);
    }

    public String getType ()
    {
        return this.type;
    }

    public int getRank ()
    {
        return this.rank;
    }

    public void upgradeRank ()
    {
        this.rank++;
    }

    public String getFaction ()
    {
        return this.faction;
    }

    public int getHealth ()
    {
        return this.fightAttributes.get(GameObjectAttribute.HEALTH);
    }

    public void setHealth (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.HEALTH, newValue);
    }

    public int getMaxHealth ()
    {
        return this.fightAttributes.get(GameObjectAttribute.MAX_HEALTH);
    }

    public void setMaxHealth (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.MAX_HEALTH, newValue);
    }

    public int getPhysicalDefence ()
    {
        return this.fightAttributes.get(GameObjectAttribute.PHYSICAL_DEFENCE);
    }

    public void setPhysicalDefence (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.PHYSICAL_DEFENCE, newValue);
    }

    public int getMana ()
    {
        return this.fightAttributes.get(GameObjectAttribute.MANA);
    }

    public void setMana (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.MANA, newValue);
    }

    public int getMaxMana ()
    {
        return this.fightAttributes.get(GameObjectAttribute.MAX_MANA);
    }

    public void setMaxMana (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.MAX_MANA, newValue);
    }

    public int getMeleeDamage ()
    {
        return this.fightAttributes.get(GameObjectAttribute.MELEE_DAMAGE);
    }

    public void setMeleeDamage (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.MELEE_DAMAGE, newValue);
    }

    public int getRangedDamage ()
    {
        return this.fightAttributes.get(GameObjectAttribute.RANGE_DAMAGE);
    }

    public void setRangedDamage (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.RANGE_DAMAGE, newValue);
    }

    public int getRemainingRangedAttacks ()
    {
        return this.fightAttributes.get(GameObjectAttribute.REMAINING_RANGED_ATTACKS);
    }

    public void setRemainingRangedAttacks (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.REMAINING_RANGED_ATTACKS, newValue);
    }

    public int getEvadeChance ()
    {
        return this.fightAttributes.get(GameObjectAttribute.EVADE_CHANCE);
    }

    public void setEvadeChance (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.EVADE_CHANCE, newValue);
    }

    public int getMagicDefence ()
    {
        return this.fightAttributes.get(GameObjectAttribute.MAGIC_DEFENCE);
    }

    public void setMagicDefence (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.MAGIC_DEFENCE, newValue);
    }

    public int getRangedAttackRange ()
    {
        return this.fightAttributes.get(GameObjectAttribute.RANGED_ATTACK_RANGE);
    }

    public void setRangedAttackRange (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.RANGED_ATTACK_RANGE, newValue);
    }

    public int getInitiative ()
    {
        return this.fightAttributes.get(GameObjectAttribute.INITIATIVE);
    }

    public void setInitiative (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.INITIATIVE, newValue);
    }

    public int getAccuracy ()
    {
        return this.fightAttributes.get(GameObjectAttribute.ACCURACY);
    }

    public void setAccuracy (int newValue)
    {
        this.fightAttributes.replace(GameObjectAttribute.ACCURACY, newValue);
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
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_ATTACK_AT_DISTANCE + distance);
        }

        if (!isMelee)
        {
            setRemainingRangedAttacks(getRemainingRangedAttacks() - 1);
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


    @Override
    public String getSpriteUrl()
    {
        if (isDead())
        {
            return AssetLocator.DEAD_SPRITE;
        }

        return this.getSpriteUrl();
    }
}
