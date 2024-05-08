package com.gitgud.model.gameObjects.interacteble.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.control.PlayerController;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.player.RessourceType;


public class ResourceCollectible extends GameObject implements Collectible
{
    private final long resourceValue;
    
    
    private final RessourceType resourceType;
    
    
    public ResourceCollectible(String name, String description, String spriteUrl, int resourceValue,
                               RessourceType resourceType)
    {
        super(name, description, spriteUrl);
        this.resourceValue = resourceValue;
        this.resourceType = resourceType;
    }
    
    
    @Override
    public void addToInventory(MissionController missionController)
    {
        PlayerController.getInstance().getPlayer().wallet().resourceMap().put(resourceType, resourceValue);
    }
}
