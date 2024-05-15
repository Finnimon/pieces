package com.gitgud.model.gameObjects.interactable.collectibles;

import com.gitgud.control.MissionController;
import com.gitgud.control.PlayerController;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.player.RessourceType;


public class ResourceCollectible extends GameObject implements Collectible
{
    private final long resourceValue;
    
    
    private final RessourceType resourceType;
    
    
    public ResourceCollectible(int resourceValue, RessourceType resourceType)
    {
        super(resourceType.name(), "description",
              "src/main/resources/com/gitgud/sprites/interactables/collectibles" + resourceType.name().toLowerCase() + ".png");
        this.resourceValue = resourceValue;
        this.resourceType = resourceType;
    }
    
    
    @Override
    public void addToInventory(MissionController missionController)
    {
        PlayerController.getInstance().getPlayer().wallet().resourceMap().put(resourceType, resourceValue);
    }
}
