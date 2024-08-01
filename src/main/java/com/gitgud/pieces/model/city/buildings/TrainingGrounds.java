package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.ResourceType;

import java.util.Collection;


/**
 * A {@link CityBuilding} with the option to train {@link FightAgent} and level them up.
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 05.06.2024
 * @Version: 1.0
 */
public class TrainingGrounds extends CityBuilding implements Transactor<FightAgent>
{
    /**
     * The name for this {@link CityBuilding}.
     */
    private static final String NAME = "Training Grounds";
    
    
    /**
     * The description for this {@link CityBuilding}.
     */
    private static final String DESCRIPTION = "Your Pieces can be trained here.";
    
    
    /**
     * The Cost per Level of a {@link FightAgent}.
     */
    private static final ResourceCost COST_PER_LEVEL = new ResourceCost(ResourceType.REDSTONE, 100);
    
    
    /**
     * Defaults to {@link TrainingGrounds#TrainingGrounds(int)}  with {@link #STARTING_LEVEL}
     */
    public TrainingGrounds()
    {
        this(STARTING_LEVEL);
    }
    
    
    /**
     * Constructs a new {@link TrainingGrounds} with the given level.
     *
     * @param level The level of the new {@link TrainingGrounds}.
     */
    public TrainingGrounds(int level)
    {
        super(NAME, DESCRIPTION, level);
    }
    
    
    /**
     * Getter for all trainable FightAgents whose training can be afforded.
     *
     * @return All trainable FightAgents whose training can be afforded.
     */
    public Collection<FightAgent> getTrainableFightAgents()
    {
        return getPlayerOwnedFightAgents().stream().filter(this::isTransactionPossible).toList();
    }
    
    
    /**
     * Getter for all player owned FightAgents.
     *
     * @return All FightAgents owned by the {@link ActiveGameController#getInstance()} player.
     */
    public Collection<FightAgent> getPlayerOwnedFightAgents()
    {
        return ActiveGameController.getInstance().get().getPlayer().army();
    }
    
    
    @Override
    public boolean isTransactionPossible(FightAgent value)
    {
        return value.getLevel() <= getLevel() && getCost(value).isResourceCostCoveredByWallet();
    }
    
    
    @Override
    public void deductCostFromInventory(FightAgent value)
    {
        getCost(value).deductResourceCostFromWallet();
    }
    
    
    @Override
    public FightAgent changeValue(FightAgent value)
    {
        value.levelUp();
        return value;
    }
    
    
    @Override
    public ResourceCost getCost(FightAgent value)
    {
        return COST_PER_LEVEL.multiple(value.getLevel()).multiple(value.getType().typeToInt());
    }
}
