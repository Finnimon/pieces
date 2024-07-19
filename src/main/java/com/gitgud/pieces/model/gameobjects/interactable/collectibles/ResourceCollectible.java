package com.gitgud.pieces.model.gameobjects.interactable.collectibles;

import com.gitgud.engine.model.gameobjects.GameObject;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.model.gameobjects.interactable.Collectible;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.player.ResourceType;


public class ResourceCollectible extends GameObject implements Collectible<MissionController>
{
    
    
    private static final String DESCRIPTION_INSERT = " Collectible ";
    
    
    private static final String DIRECTORY_PATH = "src\\main\\resources\\com\\gitgud\\pieces\\model\\gameobjects\\interactable\\collectibles\\resourcecollectible\\";
    
    
    private final long resourceValue;
    
    
    private final ResourceType resourceType;
    
    
    public ResourceCollectible(int resourceValue, ResourceType resourceType)
    {
        super(Named.formatString(resourceType.name()), determineDescription(resourceValue, resourceType),
              determineSpriteFilePath(resourceType));
        this.resourceValue = resourceValue;
        this.resourceType = resourceType;
    }
    
    
    private static String determineSpriteFilePath(ResourceType resourceType)
    {
        return DIRECTORY_PATH + resourceType.name() + DOT_PNG;
    }
    
    
    private static String determineDescription(int resourceValue, ResourceType resourceType)
    {
        return resourceValue + DESCRIPTION_INSERT + Named.formatString(resourceType.name());
    }
    
    
    @Override
    public void addToInventory()
    {
        new ResourceCost(resourceType, resourceValue).addResourceCostToWallet();
    }
    
}
