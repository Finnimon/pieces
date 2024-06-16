package com.gitgud.pieces.model.fight;

import com.gitgud.engine.model.gameObject.Describable;
import com.gitgud.engine.model.gameObject.Named;
import com.gitgud.engine.model.gameObject.Sprite;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.utility.Core;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentModifier;


/**
 * A Spell to be used in {@link Fight} by either {@link Player} or {@link FightAgent} on a {@link FightAgent}
 */
public final class Spell implements Sprite, Describable, Named
{
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteFilePath;
    
    
    private final SpellType type;
    
    
    private final FightAgentModifier modifier;
    
    
    private final int manaCost;
    
    
    private final float successChance;
    
    
    /**
     * @param name
     * @param description
     * @param spriteFilePath
     * @param type
     * @param modifier
     * @param manaCost
     * @param successChance
     */
    public Spell(String name, String description, String spriteFilePath, SpellType type, FightAgentModifier modifier,
                 int manaCost, float successChance)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
        this.type = type;
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
        return description;
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
    
    
    public SpellType getType()
    {
        return type;
    }
    
    
    public FightAgentModifier getModifier()
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
