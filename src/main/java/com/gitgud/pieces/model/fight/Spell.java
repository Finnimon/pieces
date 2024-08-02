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
import org.jetbrains.annotations.NotNull;


/**
 * A Spell to be used in {@link Fight} by either {@link Player} or {@link FightAgent} on a {@link FightAgent}
 */
public final class Spell implements Sprite, Describable, Named, Leveler, Applicable<FightAgent>
{
    private final String name;
    
    
    private final String description;
    
    
    private final String spriteFilePath;
    
    
    private final SpellType type;
    
    
    private final Applicable<FightAgent> applicable;
    
    
    private final float successChance;
    
    
    private final SimpleIntegerProperty level;
    
    
    private final SimpleIntegerProperty manaCost;
    
    
    /**
     * Constructs a new {@link Spell} from the given values.
     *
     * @param name           The name of the {@link Spell}.
     * @param description    The description of the {@link Spell}.
     * @param spriteFilePath The sprite file path of the {@link Spell}.
     * @param type           The {@link SpellType} of the {@link Spell}.
     * @param applicable     The {@link Applicable} of the {@link Spell}.
     * @param manaCost       The mana cost of the {@link Spell}.
     * @param successChance  The success chance of the {@link Spell}.
     */
    public Spell(String name, String description, String spriteFilePath, SpellType type,
                 Applicable<FightAgent> applicable, int level, int manaCost, float successChance)
    {
        this.name = name;
        this.description = description;
        this.spriteFilePath = spriteFilePath;
        this.type = type;
        this.applicable = applicable;
        this.successChance = successChance;
        this.level = new SimpleIntegerProperty(level);
        this.manaCost = new SimpleIntegerProperty();
        this.manaCost.bind(levelProperty().subtract(manaCost).multiply(-1));
    }
    
    
    @Override
    public int levelUp()
    {
        incrementLevel();
        if (getManaCost() < 0)
        {
            manaCost.set(getManaCost() - 1);
        }
        return getLevel();
    }
    
    
    @Override
    public SimpleIntegerProperty levelProperty()
    {
        return level;
    }
    
    
    /**
     * Gets the mana cost of the {@link Spell}.
     *
     * @return The mana cost of the {@link Spell}.
     */
    public int getManaCost()
    {
        return manaCost.getValue();
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
    public @NotNull String getSpriteFilePath()
    {
        return spriteFilePath;
    }
    
    
    /**
     * Gets the {@link SpellType} of the {@link Spell}.
     *
     * @return The {@link SpellType} of the {@link Spell}.
     */
    public SpellType getType()
    {
        return type;
    }
    
    
    public Applicable<FightAgent> getApplicable()
    {
        return applicable;
    }
    
    
    /**
     * Gets the success chance of the {@link Spell}.
     *
     * @return The success chance of the {@link Spell}.
     */
    public float getSuccessChance()
    {
        return successChance;
    }
    
    
    /**
     * Gets the manaCost property of the {@link Spell}.
     *
     * @return The manaCost property of the {@link Spell}.
     */
    public SimpleIntegerProperty manaCostProperty()
    {
        return manaCost;
    }
    
    
    @Override
    public FightAgent apply(@NotNull FightAgent fightAgent)
    {
        if (!doesSucceed())
        {
            return fightAgent;
        }
        return applicable.apply(fightAgent);
    }
    
    
    public boolean doesSucceed()
    {
        return Core.roll(successChance);
    }
}
