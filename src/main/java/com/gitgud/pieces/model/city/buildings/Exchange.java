package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;


public class Exchange
{
    private final ResourceCost price;
    
    
    private final ResourceCost value;
    
    
    public Exchange(ResourceCost price, ResourceCost value)
    {
        this.price = price;
        this.value = value;
    }
    
    
    public void exchange()
    {
        price.deductResourceCostFromWallet();
        value.addResourceCostToWallet();
    }
    
    
    public ResourceCost getPrice()
    {
        return price;
    }
    
    
    public ResourceCost getValue()
    {
        return value;
    }
    
    
    public Exchange changeValue(ResourceCost value)
    {
        return new Exchange(price, value);
    }
    
    
    public Exchange changePrice(ResourceCost price)
    {
        return new Exchange(price, this.value);
    }
}
