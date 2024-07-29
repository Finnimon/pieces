package com.gitgud.pieces.model.fight;

import com.gitgud.engine.model.Applicable;
import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Leveler;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.Player;
import com.gitgud.pieces.utility.Core;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * A Spell to be used in {@link Fight} by either {@link Player} or {@link FightAgent} on a {@link FightAgent}
 */
public final class Spell implements Sprite, Describable, Named, Leveler
{
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteFilePath;
    
    
    private final SpellType type;
    
    
    private final Applicable<FightAgent> applicable;
    
    
    private final float successChance;
    
    
    private SimpleIntegerProperty level;
    
    
    private SimpleIntegerProperty manaCost;
    
    
    /**
     * @param name
     * @param description
     * @param spriteFilePath
     * @param type
     * @param applicable
     * @param manaCost
     * @param successChance
     */
    public Spell(String name, String description, String spriteFilePath, SpellType type, Applicable<FightAgent> applicable,
                 int level, int manaCost, float successChance)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
        this.type = type;
        this.applicable = applicable;
        this.manaCost =new SimpleIntegerProperty( manaCost);
        this.successChance = successChance;
        this.level = new SimpleIntegerProperty(level);
        this.manaCost.bind(this.manaCost.subtract(levelProperty()));
    }
    
    
    
    @Override
    public int levelUp()
    {
        incrementLevel();
        
        return getLevel();
    }
    
    
    @Override
    public SimpleIntegerProperty levelProperty()
    {
        return level;
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
    
    
    public Applicable<FightAgent> getApplicable()
    {
        return applicable;
    }
    
    
    public int getManaCost()
    {
        return manaCost.getValue();
    }
    
    
    public float getSuccessChance()
    {
        return successChance;
    }
    
    
    public SimpleIntegerProperty manaCostProperty()
    {
        return manaCost;
    }
}
