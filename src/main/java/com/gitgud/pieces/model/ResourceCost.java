package com.gitgud.pieces.model;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.player.ResourceType;
import javafx.beans.property.SimpleLongProperty;

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
    
    
    public ResourceCost multiple(int multiplier)
    {
        HashMap<ResourceType, Long> newResourceCostMap = new HashMap<>();
        for (ResourceType resourceType : resourceCostMap.keySet())
        {
            newResourceCostMap.put(resourceType, resourceCostMap.get(resourceType) * multiplier);
        }
        return new ResourceCost(newResourceCostMap);
    }
    
    
    public boolean isResourceCostCoveredByWallet(int multiplier)
    {
        return multiple(multiplier).isResourceCostCoveredByWallet();
    }
    
    
    public boolean isResourceCostCoveredByWallet()
    {
        
        HashMap<ResourceType, SimpleLongProperty> walletResourceMap = ActiveGameController.getInstance().get().getPlayer().wallet().resourceMap();
        for (ResourceType resourceType : resourceCostMap.keySet())
        {
            if (walletResourceMap.get(resourceType).getValue() < resourceCostMap.get(resourceType))
            {
                return false;
            }
        }
        return true;
    }
    
    
    public void deductResourceCostFromWallet(int multiplier)
    {
        multiple(multiplier).deductResourceCostFromWallet();
    }
    
    
    public void deductResourceCostFromWallet()
    {
        multiple(-1).deductResourceCostFromWallet();
    }
    
    
    public void addResourceCostToWallet(int multiplier)
    {
        multiple(multiplier).addResourceCostToWallet();
    }
    
    
    public void addResourceCostToWallet()
    {
        for (ResourceType resourceType : resourceCostMap.keySet())
        {
            HashMap<ResourceType, SimpleLongProperty> walletResourceMap = ActiveGameController.getInstance().get().getPlayer().wallet().resourceMap();
            SimpleLongProperty valueProperty = walletResourceMap.get(resourceType);
            valueProperty.setValue(valueProperty.getValue() + resourceCostMap.get(resourceType));
        }
    }
}
