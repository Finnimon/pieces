package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.fight.Spell;
import com.gitgud.pieces.model.player.ResourceType;


public class MagicEnhancer extends CityBuilding implements Transactor<Spell>
{
    private static final ResourceCost COST_PER_LEVEL = new ResourceCost(ResourceType.SILVER, 1500);
    
    
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
    
    
    public ResourceCost getCost(Spell spell)
    {
        return COST_PER_LEVEL.multiple(spell.getLevel());
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
    public int levelUp()
    {
        if (!getLevelUpCost().isResourceCostCoveredByWallet())
        {
            return getLevel();
        }
        
        getLevelUpCost().deductResourceCostFromWallet();
        return super.levelUp();
    }
    
    
    public ResourceCost getLevelUpCost()
    {
        return LEVEL_UP_COST.multiple(getLevel());
    }
}
