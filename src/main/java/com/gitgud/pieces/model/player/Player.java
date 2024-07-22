package com.gitgud.pieces.model.player;

import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.pieces.model.fight.SpellBook;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;

import java.util.HashSet;


/**
 * Global Object that stores the Player's current abilities artifacts etc for use in city, mission and fight
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2022
 */
public record Player(String name, Difficulty difficulty, HashSet<FightAgent> army, Wallet wallet, ArtefactPouch artefactPouch,
                     SpellBook spellbook) implements Named
{
}
