package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.fight.Allegiance;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.FightAgentType;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;

import static com.gitgud.pieces.model.city.buildings.CityConstants.*;


public class FactionCamp extends CityBuilding implements Transactor<FightAgent>
{
    private static final String DEFAULT_DESCRIPTION = "Pieces of it's faction can be recruited here.";
    
    
    private static final String NAME = " Faction Camp";
    
    
    private static final String AGENT_NOT_AVAILABLE = "FightAgent not available";
    
    
    private final Faction faction;
    
    
    public FactionCamp(Faction faction)
    {
        this(faction, STARTING_LEVEL);
    }
    
    
    public FactionCamp(Faction faction, int level)
    {
        this(faction + NAME, DEFAULT_DESCRIPTION, level, faction);
    }
    
    
    private FactionCamp(String name, String description, int level, Faction faction)
    {
        super(name, description, level);
        this.faction = faction;
    }
    
    
    public ResourceCost getLevelUpResourceCost()
    {
        return LEVEL_UP_RESOURCE_COST.multiple(getLevel());
    }
    
    
    public FightAgent[][] getRecruitableFightAgentsWithCost()
    {
        int buildingLevel = getLevel();
        FightAgent[][] recruitableFightAgents = new FightAgent[buildingLevel][FightAgentType.values().length];
        
        Faction faction = getFaction();
        
        FightAgentDirector director = new FightAgentDirector();
        for (int level = 1; level <= buildingLevel; level++)
        {
            for (FightAgentType type : FightAgentType.values())
            {
                recruitableFightAgents[level][type.ordinal()] = (director.make(Allegiance.BLACK, type, faction, level));
            }
        }
        return recruitableFightAgents;
    }
    
    
    public Faction getFaction()
    {
        return faction;
    }
    
    
    @Override
    public boolean isTransactionPossible(FightAgent fightAgent)
    {
        ResourceCost resourceCost = getCost(fightAgent);
        if (resourceCost == null)
        {
            return false;
        }
        return resourceCost.isResourceCostCoveredByWallet();
    }
    
    
    @Override
    public void deductCostFromInventory(FightAgent fightAgent)
    {
        ResourceCost resourceCost = getCost(fightAgent);
        if (resourceCost == null)
        {
            throw new IllegalArgumentException(AGENT_NOT_AVAILABLE);
        }
        resourceCost.deductResourceCostFromWallet();
    }
    
    
    @Override
    public FightAgent changeValue(FightAgent value)
    {
        ActiveGameController.getInstance().get().getPlayer().army().add(value);
        return value;
    }
    
    @Override
    public ResourceCost getCost(FightAgent fightAgent)
    {
        ResourceCost baseCost;
        FightAgentType type = fightAgent.getType();
        
        baseCost = switch (type)
        {
            case PAWN -> PAWN_COST;
            case KNIGHT -> KNIGHT_COST;
            case ROOK -> ROOK_COST;
            case BISHOP -> BISHOP_COST;
            case QUEEN -> QUEEN_COST;
        };
        
        int fightAgentLevel = fightAgent.getLevel();
        return baseCost.multiple(fightAgentLevel * fightAgentLevel);
    }
    
    
    @Override
    public int levelUp()
    {
        if (!LEVEL_UP_RESOURCE_COST.isResourceCostCoveredByWallet(getLevel()))
        {
            return getLevel();
        }
        
        LEVEL_UP_RESOURCE_COST.deductResourceCostFromWallet(getLevel());
        
        
        return super.levelUp();
    }
}
