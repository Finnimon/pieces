package com.gitgud.model.gameObjects.interactable.collectibles;

import com.gitgud.control.ActiveGameController;
import com.gitgud.control.MissionController;
import com.gitgud.model.gameObjects.GameObject;
import com.gitgud.model.player.ResourceType;


public class ResourceCollectible extends GameObject implements Collectible
{
    private final long resourceValue;
    
    
    private final ResourceType resourceType;
    
    
    public ResourceCollectible(int resourceValue, ResourceType resourceType)
    {
        super(resourceType.name(), "description",
              "src\\main\\resources\\com\\gitgud\\sprites\\interactables\\collectibles\\" + resourceType.name().toLowerCase() + ".png");
        this.resourceValue = resourceValue;
        this.resourceType = resourceType;
    }
    
    
    @Override
    public void addToInventory(MissionController missionController)
    {
        ActiveGameController.getInstance().get().getPlayer().wallet().resourceMap().put(resourceType, resourceValue);
    }
}
