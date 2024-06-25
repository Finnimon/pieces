package com.gitgud.pieces.model.fight;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Leveler;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.utility.modification.Modifier;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.utility.Core;
import com.gitgud.pieces.utility.modification.fightAgent.FightAgentModifier;

import java.util.List;


/**
 * A Spell to be used in {@link Fight} by either {@link Player} or {@link FightAgent} on a {@link FightAgent}
 */
public final class Spell implements Sprite, Describable, Named, Leveler
{
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteFilePath;
    
    
    private final SpellType type;
    
    
    private final FightAgentModifier modifier;
    
    
    private final float successChance;
    
    
    private int level;
    
    
    private int manaCost;
    
    
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
                 int level, int manaCost, float successChance)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
        this.type = type;
        this.modifier = modifier;
        this.manaCost = manaCost;
        this.successChance = successChance;
        this.level = level;
    }
    
    
    @Override
    public int getLevel()
    {
        return level;
    }
    
    
    @Override
    public int levelUp()
    {
        manaCost = manaCost / level * (level++);
        
        List<Modifier<FightAgent>> modifiers = modifier.getModifiers();
        if (level == 1)
        {
            modifiers.addAll(modifiers);
            
            return level;
        }
        addAllOriginalModifiers(modifiers);
        
        
        return level;
    }
    
    
    private void addAllOriginalModifiers(List<Modifier<FightAgent>> modifiers)
    {
        for (int i = 0; i < modifiers.size() - level - 1; i++)
        {
            modifiers.add(modifiers.get(i));
        }
    }
    
    
    @Override
    public String name()
    {
        return name;
    }
    
    
    @Override
    public String description()
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
