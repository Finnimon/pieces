package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.player.ResourceType;


/**
 * CityBuilding where you can buy and sell resources for other resources.
 
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 25.06.2024
 */
public class Market extends CityBuilding
{
    private static final double PRICE_REDUCTION_PER_LEVEL = 0.9;
    
    
    private static final ResourceCost costPerLevel = new ResourceCost(ResourceType.GOLD, 1000L);
    
    
    private Exchange platinumExchange;
    
    
    private Exchange goldExchange;
    
    
    private Exchange silverExchange;
    
    
    private Exchange ironExchange;
    
    
    private Exchange copperExchange;
    
    
    private Exchange redstoneExchange;
    
    
    public Market()
    {
        this(new Exchange(new ResourceCost(ResourceType.GOLD, 100L), new ResourceCost(ResourceType.PLATINUM, 5L)),
             new Exchange(new ResourceCost(ResourceType.SILVER, 100L), new ResourceCost(ResourceType.GOLD, 5L)),
             new Exchange(new ResourceCost(ResourceType.COPPER, 100L), new ResourceCost(ResourceType.SILVER, 10L)),
             new Exchange(new ResourceCost(ResourceType.IRON, 100L), new ResourceCost(ResourceType.COPPER, 20L)),
             new Exchange(new ResourceCost(ResourceType.REDSTONE, 100L), new ResourceCost(ResourceType.IRON, 40L)),
             new Exchange(new ResourceCost(ResourceType.COPPER, 100L), new ResourceCost(ResourceType.REDSTONE, 1000L)));
    }
    
    
    public Market(Exchange platinumExchange, Exchange goldExchange, Exchange silverExchange, Exchange ironExchange,
                  Exchange copperExchange, Exchange redstoneExchange)
    {
        super("Market", "Exchange resources here.", 1);
        this.platinumExchange = platinumExchange;
        this.goldExchange = goldExchange;
        this.silverExchange = silverExchange;
        this.ironExchange = ironExchange;
        this.copperExchange = copperExchange;
        this.redstoneExchange = redstoneExchange;
    }
    
    
    public Exchange getPlatinumExchange()
    {
        return platinumExchange;
    }
    
    
    public Exchange getGoldExchange()
    {
        return goldExchange;
    }
    
    
    public Exchange getSilverExchange()
    {
        return silverExchange;
    }
    
    
    public Exchange getIronExchange()
    {
        return ironExchange;
    }
    
    
    public Exchange getCopperExchange()
    {
        return copperExchange;
    }
    
    
    public Exchange getRedstoneExchange()
    {
        return redstoneExchange;
    }
    
    
    public void levelUpIfPossible()
    {
        if (!getLevelUpCost().isResourceCostCoveredByWallet())
        {
            return;
        }
        levelUp();
    }
    
    
    public ResourceCost getLevelUpCost()
    {
        return costPerLevel.multiple(getLevel());
    }
    
    
    @Override
    public int levelUp()
    {
        getLevelUpCost().deductResourceCostFromWallet();
        platinumExchange = levelUp(platinumExchange);
        goldExchange = levelUp(goldExchange);
        silverExchange = levelUp(silverExchange);
        ironExchange = levelUp(ironExchange);
        copperExchange = levelUp(copperExchange);
        redstoneExchange = levelUp(redstoneExchange);
        
        super.levelUp();
        
        
        return getLevel();
    }
    
    
    private static Exchange levelUp(Exchange exchange)
    {
        return exchange.changePrice(exchange.getPrice().multiple(PRICE_REDUCTION_PER_LEVEL));
    }
}
