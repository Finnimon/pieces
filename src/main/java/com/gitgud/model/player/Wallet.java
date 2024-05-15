package com.gitgud.model.player;


import java.util.HashMap;


/**
 * Stores the different global resourceMap used in the game.
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 19.04.2022
 */
public record Wallet(HashMap<RessourceType, Long> resourceMap)
{

}
