package com.gitgud.pieces.model.gameobjects.agents;

import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.fight.SpellCaster;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;


/**
 * Expands FightAgents with {@link SpellCaster} functionality.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 16.06.2024
 * @Version: 1.2
 * @see FightAgent
 * @see SpellCaster
 */
public class SpellCasterFightAgent extends FightAgent implements SpellCaster
{
    
    private final SpellBook spellBook;
    
    
    public SpellCasterFightAgent(String name, String description, String spriteUrl, boolean isFlying, int movementRange,
                                 FightAgentType fightAgentType, Faction faction, Allegiance allegiance, int rank,
                                 int meleeDamage, int rangedDamage, int rangedAttackRange, int remainingRangedAttacks,
                                 boolean isRangedAttacker, int physicalDefence, int magicDefence, float evadeChance,
                                 int maxHealth, int maxMana, int health, int mana, int initiative, float accuracy,
                                 SpellBook spellBook)
    {
        super(name,
              description,
              spriteUrl,
              isFlying,
              movementRange,
              fightAgentType,
              faction,
              allegiance,
              rank,
              meleeDamage,
              rangedDamage,
              rangedAttackRange,
              remainingRangedAttacks,
              isRangedAttacker,
              physicalDefence,
              magicDefence,
              evadeChance,
              maxHealth,
              maxMana,
              health,
              mana,
              initiative,
              accuracy);
        this.spellBook = spellBook;
    }
    
    
    @Override
    public SpellBook getSpellbook()
    {
        return this.spellBook;
    }
    
}
