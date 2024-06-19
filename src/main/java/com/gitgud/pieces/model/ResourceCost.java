package com.gitgud.pieces.model;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.player.ResourceType;

import java.util.HashMap;


public class ResourceCost
{
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
        int defaultMultiplier = 1;
        return isResourceCostCoveredByWallet(defaultMultiplier);
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
        int defaultMultiplier = 1;
        deductResourceCostFromWallet(defaultMultiplier);
    }
}
