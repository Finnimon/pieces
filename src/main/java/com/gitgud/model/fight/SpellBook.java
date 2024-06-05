package com.gitgud.model.fight;


import java.util.HashMap;
import java.util.HashSet;


/**
 * Stores the Spells available for use to the player in a {@link Spell}
 * @param spells The {@link Spell} available to the player.
 * @author Finn L.
 * @Owner: Finn L.
 * @version 1.0
 * @since 19.04.2022
 */
public record SpellBook(HashSet< Spell> spells)
{


}
