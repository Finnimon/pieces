package com.gitgud.pieces.model.fight;


import com.gitgud.engine.control.action.ToAction;
import com.gitgud.engine.control.actionChoice.ActionChoice;
import com.gitgud.engine.control.actionChoice.RootActionChoice;
import com.gitgud.engine.control.actionChoice.RootToActionChoice;
import com.gitgud.engine.control.actionChoice.ToActionChoice;
import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.map.GridMap;
import com.gitgud.engine.model.map.Tile;
import com.gitgud.pieces.control.FightController;
import com.gitgud.pieces.control.action.SpellCasterToAction;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import javafx.beans.property.SimpleIntegerProperty;
import org.jetbrains.annotations.NotNull;

import java.util.*;


/**
 * An Agent that can cast spells on other agents.
 * Contains the Logic for creating SpellChoices for the FightController.
 *
 * @author Finn L.
 * @version 2.0
 * @Owner: Finn L.
 * @see Spell
 * @see SpellBook
 * @since 30.06.2022
 */
public interface SpellCaster extends Named, Describable
{
    /**
     * Returns the rootActionChoice for this Spellcasters possible spells.
     *
     * @param fightController The fightcontroller to act on.
     * @return The possible spell rootchoice for this {@link SpellCaster}.
     */
    default RootActionChoice possibleSpellChoices(FightController fightController)
    {
        HashMap<Spell, HashSet<Tile>> spellsTargets = getSpellsTargets(fightController.getModel());
        List<ActionChoice> choices = new ArrayList<>(spellsTargets.size());
        for (Spell spell : spellsTargets.keySet())
        {
            if (!canCast(spell))
            {
                continue;
            }
            choices.add(possibleSpellTargetChoices(spell, spellsTargets.get(spell), fightController));
        }
        return new RootActionChoice(name(), description(), fightController, choices);
    }
    
    
    /**
     * Gets the possible targets of all spells of this {@link SpellCaster}.
     *
     * @param fight The current fight.
     * @return The possible targets of all spells of this {@link SpellCaster}.
     */
    private HashMap<Spell, HashSet<Tile>> getSpellsTargets(Fight fight)
    {
        HashMap<Spell, HashSet<Tile>> targets = new HashMap<>();
        GridMap<FightAgent> gridMap = fight.getGridMap();
        
        for (Spell spell : getSpellbook().spells())
        {
            targets.put(spell, getSpellTargets(spell, gridMap));
        }
        return targets;
    }
    
    
    /**
     * Determines if {@code spell} can be cast.
     *
     * @param spell The spell that is determined to be castable.
     * @return Whether {@code spell} can be cast.
     */
    default boolean canCast(Spell spell)
    {
        return spell.getManaCost() <= getMana();
    }
    
    
    private RootToActionChoice possibleSpellTargetChoices(Spell spell, HashSet<Tile> tiles,
                                                          FightController fightController)
    {
        List<ToActionChoice> toActionChoices = new ArrayList<>();
        
        for (Tile tile : tiles)
        {
            ToAction toAction = new SpellCasterToAction(tile, fightController, spell, this);
            ToActionChoice toActionChoice = new ToActionChoice<>(spell.name(),
                                                                 spell.description(),
                                                                 fightController,
                                                                 toAction);
            toActionChoices.add(toActionChoice);
        }
        
        return new RootToActionChoice(spell.name(), spell.description(), fightController, toActionChoices);
    }
    
    
    SpellBook getSpellbook();
    
    
    private HashSet<Tile> getSpellTargets(Spell spell, GridMap<FightAgent> gridMap)
    {
        Allegiance targetedAllegiance = getSpellTargetedAllegiance(spell);
        HashSet<Tile> targets = new HashSet<>();
        
        for (Tile tile : gridMap.verticeSet())
        {
            FightAgent agent = gridMap.get(tile);
            if (agent == null)
            {
                continue;
            }
            if (agent.getAllegiance() != targetedAllegiance)
            {
                continue;
            }
            targets.add(tile);
        }
        
        return targets;
    }
    
    
    default int getMana()
    {
        return manaProperty().get();
    }
    
    
    /**
     * Returns the {@link Allegiance} of FightAgent targeted by {@code spell}.
     *
     * @param spell The Spell whose targeted allegiance will be determined.
     * @return The Allegiance targeted by {@code spell}.
     */
    private Allegiance getSpellTargetedAllegiance(Spell spell)
    {
        boolean isFriendlyTargeting = spell.getType().getIsFriendlyTargeting();
        
        return isFriendlyTargeting ?
               getAllegiance() :
               Arrays.stream(Allegiance.values()).filter(a -> !a.equals(getAllegiance())).findFirst().get();
    }
    
    
    /**
     * Gets the Property with the current mana of the SpellCaster as value;
     *
     * @return The Property with the current mana of the SpellCaster as value;
     */
    SimpleIntegerProperty manaProperty();
    
    
    /**
     * Gets the {@link Allegiance} of this SpellCaster.
     *
     * @return The {@link Allegiance} of this SpellCaster.
     */
    Allegiance getAllegiance();
    
    
    /**
     * Setter for mana.
     *
     * @param mana The new value for mana.
     */
    default void setMana(int mana)
    {
        manaProperty().set(mana);
    }
    
    
    default void cast(@NotNull Spell spell, @NotNull FightAgent fightAgent)
    {
        deductManaCost(spell);
        spell.apply(fightAgent);
    }
    
    
    /**
     * Deducts the mana cost of {@code spell} from {@link #manaProperty()};
     *
     * @param spell The spell whose manaCost will be deducted.
     */
    private void deductManaCost(@NotNull Spell spell)
    {
        setMana(getMana() - spell.getManaCost());
    }
}
