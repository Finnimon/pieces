package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact;
import com.gitgud.pieces.model.player.ResourceType;


/**
 * This class represents the BlackSmith. It has functionality to upgrade an
 * {@link com.gitgud.pieces.model.gameobjects.interactable.collectibles.Artefact}
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 25.06.2024
 */
public class BlackSmith extends CityBuilding implements Transactor<Artefact>
{
    
    
    private final static ResourceCost TRANSACT_COST = new ResourceCost(ResourceType.PLATINUM, 10000);
    
    
    private static final String DESCRIPTION = "Upgrade your Artefacts Here for the cost of " +
                                              TRANSACT_COST +
                                              " per Level ";
    
    
    private static final String NAME = "BlackSmith";
    
    
    public BlackSmith()
    {
        this(STARTING_LEVEL);
    }
    
    
    public BlackSmith(int level)
    {
        super(NAME, DESCRIPTION, level);
    }
    
    
    @Override
    public boolean isTransactionPossible(Artefact artefact)
    {
        return artefact.getLevel() <= getLevel() && getCost(artefact).isResourceCostCoveredByWallet();
    }
    
    
    @Override
    public void deductCostFromInventory(Artefact artefact)
    {
        getCost(artefact).deductResourceCostFromWallet();
    }
    
    
    @Override
    public Artefact changeValue(Artefact value)
    {
        value.levelUp();
        
        return value;
    }
    
    
    @Override
    public ResourceCost getCost(Artefact artefact)
    {
        return TRANSACT_COST.multiple(artefact.getLevel());
    }
}
