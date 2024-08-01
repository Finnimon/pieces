package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.player.ResourceType;


/**
 * CityBuilding where Spells can be improved
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 25.06.2024
 */
public class MagicEnhancer extends CityBuilding implements Transactor<Spell>
{
    /**
     * The base cost for transacting a spell.
     */
    private static final ResourceCost TRANSACT_COST = new ResourceCost(ResourceType.SILVER, 1500);
    
    
    /**
     * The cost for leveling up this building.
     */
    private static final ResourceCost LEVEL_UP_COST = new ResourceCost(ResourceType.COPPER, 10000);
    
    
    public MagicEnhancer()
    {
        super("Magic Enhancer", "Enhance your magic here.", STARTING_LEVEL);
    }
    
    
    @Override
    public boolean isTransactionPossible(Spell spell)
    {
        return getLevel() >= spell.getLevel() && getCost(spell).isResourceCostCoveredByWallet();
    }
    
    
    @Override
    public void deductCostFromInventory(Spell spell)
    {
        getCost(spell).deductResourceCostFromWallet();
    }
    
    
    @Override
    public Spell changeValue(Spell value)
    {
        value.levelUp();
        return value;
    }
    
    
    @Override
    public ResourceCost getCost(Spell spell)
    {
        return TRANSACT_COST.multiple(spell.getLevel());
    }
    
    
    @Override
    public int levelUp()
    {
        if (!getLevelUpCost().isResourceCostCoveredByWallet())
        {
            return getLevel();
        }
        
        getLevelUpCost().deductResourceCostFromWallet();
        return super.levelUp();
    }
    
    
    /**
     * Gets the current cost to level up this building.
     *
     * @return The current cost to level up this building.
     */
    public ResourceCost getLevelUpCost()
    {
        return LEVEL_UP_COST.multiple(getLevel());
    }
}
