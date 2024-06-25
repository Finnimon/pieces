package com.gitgud.pieces.model;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.player.ResourceType;

import java.util.HashMap;


public class ResourceCost
{
    
    
    public static final int DEFAULT_MULTIPLIER = 1;
    
    
    private final HashMap<ResourceType, Long> resourceCostMap;
    
    
    public ResourceCost(HashMap<ResourceType, Long> resourceCostMap)
    {
        this.resourceCostMap = resourceCostMap;
    }
    
    
    public ResourceCost(ResourceType resourceType, long amount)
    {
        resourceCostMap = new HashMap<>();
        resourceCostMap.put(resourceType, amount);
    }
    
    
    public boolean isResourceCostCoveredByWallet(int multiplier)
    {
        HashMap<ResourceType, Long> walletResourceMap = ActiveGameController.getInstance().get().getPlayer().wallet().resourceMap();
        for (ResourceType resourceType : resourceCostMap.keySet())
        {
            if (walletResourceMap.get(resourceType) < resourceCostMap.get(resourceType) * multiplier)
            {
                return false;
            }
        }
        return true;
    }
    
    
    public boolean isResourceCostCoveredByWallet()
    {
        return isResourceCostCoveredByWallet(DEFAULT_MULTIPLIER);
    }
    
    
    public void deductResourceCostFromWallet(int multiplier)
    {
        for (ResourceType resourceType : resourceCostMap.keySet())
        {
            HashMap<ResourceType, Long> walletResourceMap = ActiveGameController.getInstance().get().getPlayer().wallet().resourceMap();
            walletResourceMap.put(resourceType,
                                  walletResourceMap.get(resourceType) - resourceCostMap.get(resourceType) * multiplier);
        }
    }
    
    
    public void deductResourceCostFromWallet()
    {
        deductResourceCostFromWallet(DEFAULT_MULTIPLIER);
    }
    
    
    public void addResourceCostToWallet(int multiplier)
    {
        for (ResourceType resourceType : resourceCostMap.keySet())
        {
            HashMap<ResourceType, Long> walletResourceMap = ActiveGameController.getInstance().get().getPlayer().wallet().resourceMap();
            walletResourceMap.put(resourceType,
                                  walletResourceMap.get(resourceType) + resourceCostMap.get(resourceType) * multiplier);
        }
    }
    
    
    public void addResourceCostToWallet()
    {
        addResourceCostToWallet(DEFAULT_MULTIPLIER);
    }
}
