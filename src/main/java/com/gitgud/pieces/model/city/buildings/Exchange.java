package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;


/**
 * Helper class for exchanging resources in a {@link Market}.
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @see Market
 * @since 25.06.2024
 */
public record Exchange(ResourceCost price, ResourceCost value)
{
    
    
    public void exchange()
    {
        price.deductResourceCostFromWallet();
        value.addResourceCostToWallet();
    }
    
    
    public Exchange differentValue(ResourceCost value)
    {
        return new Exchange(price, value);
    }
    
    
    public Exchange differentPrice(ResourceCost price)
    {
        return new Exchange(price, this.value);
    }
}
