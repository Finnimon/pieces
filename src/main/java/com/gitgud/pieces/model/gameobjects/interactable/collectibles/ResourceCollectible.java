package com.gitgud.pieces.model.gameobjects.interactable.collectibles;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.player.ResourceType;


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
    public void addToInventory()
    {
        ActiveGameController.getInstance().get().getPlayer().wallet().resourceMap().put(resourceType, resourceValue);
    }
}
