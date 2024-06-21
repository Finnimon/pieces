package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;
import com.gitgud.pieces.model.player.ResourceType;


public class BlackSmith extends CityBuilding implements Transactor<Artefact>
{
    private static final ResourceType REQUIRED_RESOURCE = ResourceType.PLATINUM;
    
    
    private final static ResourceCost COST_PER_LEVEL = new ResourceCost(REQUIRED_RESOURCE, 10);
    
    
    private static final String NAME = "BlackSmith";
    
    
    private static final String DESCRIPTION = "Upgrade your Artefacts Here for the cost of " + COST_PER_LEVEL + REQUIRED_RESOURCE + " per Level ";
    
    
    public BlackSmith(int level)
    {
        super(NAME, DESCRIPTION, level);
    }
    
    
    @Override
    public boolean isTransactionPossible(Artefact artefact)
    {
        return artefact.getLevel() < getLevel() && COST_PER_LEVEL.isResourceCostCoveredByWallet(artefact.getLevel());
    }
    
    
    @Override
    public void deductCostFromInventory(Artefact artefact)
    {
        COST_PER_LEVEL.deductResourceCostFromWallet(artefact.getLevel());
    }
    
    
    @Override
    public Artefact changeValue(Artefact value)
    {
        value.levelUp();
        
        
        return value;
    }
    
}
