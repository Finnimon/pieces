package com.gitgud.control;

import com.gitgud.model.player.Player;


public class PlayerController
{
    private static PlayerController playerControllerSingleton = null;
    
    
    private final Player player;
    
    
    private PlayerController(Player player)
    {
        this.player = player;
    }
    
    
    public static PlayerController getInstance()
    {
        if (playerControllerSingleton == null)
        {
            throw new IllegalStateException("Not initialized");
        }
        
        
        return playerControllerSingleton;
    }
    
    
    public static void initialize(Player player)
    {
        if (playerControllerSingleton != null)
        {
            throw new IllegalStateException("Already initialized");
        }
        
        
        playerControllerSingleton = new PlayerController(player);
    }
    
    
    public Player getPlayer()
    {
        return player;
    }
}
