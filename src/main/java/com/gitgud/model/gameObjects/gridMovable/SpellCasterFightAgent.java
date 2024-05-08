package com.gitgud.model.gameObjects.gridMovable;

import com.gitgud.model.gameObjects.FightFigureFaction;
import com.gitgud.model.gameObjects.FightFigureType;
import com.gitgud.model.fight.SpellBook;
import com.gitgud.model.fight.SpellCaster;


public class SpellCasterFightAgent extends FightAgent implements SpellCaster
{
    
    public SpellCasterFightAgent(String name, String description, String spriteUrl, boolean isFlying, int movementRange,
                                 FightFigureType fightFigureType, FightFigureFaction fightFigureFaction,
                                 int meleeDamage, int rangedDamage, int rangedAttackRange, int remainingRangedAttacks,
                                 int physicalDefence, int magicDefence, float evadeChance, int maxHealth, int maxMana,
                                 int health, int mana, int initiative, float accuracy)
    {
        super(name, description, spriteUrl, isFlying, movementRange, fightFigureType, fightFigureFaction, meleeDamage,
              rangedDamage, rangedAttackRange, remainingRangedAttacks, physicalDefence, magicDefence, evadeChance,
              maxHealth, maxMana, health, mana, initiative, accuracy);
    }
    
    
    @Override
    public SpellBook getSpellbook()
    {
        return null;
    }
}
