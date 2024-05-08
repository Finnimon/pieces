package com.gitgud.model.player;

import com.gitgud.model.mission.fight.SpellBook;


/**
 * Global Object that stores the Player's current abilities artifacts etc for use in city, mission and fight
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2022
 */
public record Player(Army army, Wallet wallet, ArtefactPouch artefactPouch, SpellBook spellbook)
{
}
