package com.gitgud.model.fight;

import com.gitgud.model.gameObjects.Describable;
import com.gitgud.model.gameObjects.Named;
import com.gitgud.model.gameObjects.Sprite;
import com.gitgud.model.gameObjects.gridMovable.FightAgentFL;
import com.gitgud.model.player.Player;
import com.gitgud.utility.Core;
import com.gitgud.utility.modification.FightAgentModifier;


/**
 * A Spell to be used in {@link Fight} by either {@link Player} or {@link FightAgentFL} on a {@link FightAgentFL}
 */
public final class Spell implements Sprite, Describable, Named
{
    private final String name;
    
    
    private final String Description;
    
    
    private final String spriteFilePath;
    
    
    private final SpellType spellType;
    
    
    private final FightAgentModifier[] modifiers;
    
    
    private final int manaCost;
    
    
    private final float successChance;
    
    
    private final Allegiance targeting;
    
    
    /**
     * @param name
     * @param description
     * @param spriteFilePath
     * @param spellType
     * @param modifiers
     * @param manaCost
     * @param successChance
     */
    public Spell(String name, String description, String spriteFilePath, SpellType spellType,
                 FightAgentModifier[] modifiers, int manaCost, float successChance, Allegiance targeting)
    {
        this.name = name;
        Description = description;
        this.spriteFilePath = spriteFilePath;
        this.spellType = spellType;
        this.modifiers = modifiers;
        this.manaCost = manaCost;
        this.successChance = successChance;
        this.targeting = targeting;
    }
    
    
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {

    }


    @Override
    public String getDescription()
    {
        return Description;
    }

    @Override
    public void setDescription(String description)
    {

    }


    @Override
    public String getSpriteUrl()
    {
        return spriteFilePath;
    }

    @Override
    public void setSpriteUrl(String spriteUrl)
    {

    }


    public boolean doesSucceed()
    {
        return Core.roll(successChance);
    }
    
    
    public SpellType getSpellType()
    {
        return spellType;
    }
    
    
    public FightAgentModifier[] getModifiers()
    {
        return modifiers;
    }
    
    
    public int getManaCost()
    {
        return manaCost;
    }
    
    
    public float getSuccessChance()
    {
        return successChance;
    }
    
    
    public Allegiance getTargeting()
    {
        return targeting;
    }
}
