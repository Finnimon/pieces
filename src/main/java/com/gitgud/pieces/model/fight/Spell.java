package com.gitgud.pieces.model.fight;

import com.gitgud.engine.model.gameObject.Describable;
import com.gitgud.engine.model.gameObject.Named;
import com.gitgud.engine.model.gameObject.Sprite;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.utility.Core;
import com.gitgud.engine.utility.modification.Modifier;


/**
 * A Spell to be used in {@link Fight} by either {@link Player} or {@link FightAgent} on a {@link FightAgent}
 */
public final class Spell implements Sprite, Describable, Named
{
    private final String name;
    
    
    private final String Description;
    
    
    private final String spriteFilePath;
    
    
    private final SpellType spellType;
    
    
    private final Modifier<FightAgent> modifier;
    
    
    private final int manaCost;
    
    
    private final float successChance;
    
    
    
    /**
     * @param name
     * @param description
     * @param spriteFilePath
     * @param spellType
     * @param modifier
     * @param manaCost
     * @param successChance
     */
    public Spell(String name, String description, String spriteFilePath, SpellType spellType,
                 Modifier<FightAgent> modifier, int manaCost, float successChance)
    {
        this.name = name;
        Description = description;
        this.spriteFilePath = spriteFilePath;
        this.spellType = spellType;
        this.modifier = modifier;
        this.manaCost = manaCost;
        this.successChance = successChance;
    }
    
    
    @Override
    public String getName()
    {
        return name;
    }
    
    
    @Override
    public String getDescription()
    {
        return Description;
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
    
    public boolean doesSucceed()
    {
        return Core.roll(successChance);
    }
    
    
    public SpellType getSpellType()
    {
        return spellType;
    }
    
    
    public Modifier<FightAgent> getModifier()
    {
        return modifier;
    }
    
    
    public int getManaCost()
    {
        return manaCost;
    }
    
    
    public float getSuccessChance()
    {
        return successChance;
    }
    
}
