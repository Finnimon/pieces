package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.gameobjects.Faction;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.player.ResourceType;
import com.gitgud.pieces.utility.builder.fightAgent.FightAgentDirector;

import java.util.HashMap;


public class FactionCamp extends CityBuilding implements Transactor<FightAgent>
{
    
    
    private static final ResourceCost LEVEL_UP_RESOURCE_COST = new ResourceCost(ResourceType.PLATINUM, 100);
    
    
    private static final Faction DEFAULT_FACTION = Faction.MONOCHROME;
    
    
    private static final String DEFAULT_DESCRIPTION = "Pieces of it's faction can be recruited here.";
    
    
    private static final String NAME = " Faction Camp";
    
    
    private static final String AGENT_NOT_AVAILABLE = "FightAgent not available";
    
    
    private final Faction faction;
    
    
    private FactionCamp(String name, String description, int level, Faction faction)
    {
        super(name, description, level);
        this.faction = faction;
    }
    
    
    public static FactionCamp create(Faction faction, int level)
    {
        return new FactionCamp(faction + NAME, DEFAULT_DESCRIPTION, level, faction);
    }
    
    
    public static FactionCamp create(Faction faction)
    {
        return create(faction, STARTING_LEVEL);
    }
    
    
    public HashMap<FightAgent, ResourceCost> loadRecruitableFightAgentsWithCost()
    {
        
        int level = getLevel();
        Faction faction = getFaction();
        
        FightAgentDirector director = new FightAgentDirector();
        
        
        return null;
    }
    
    
    @Override
    public boolean isTransactionPossible(FightAgent fightAgent)
    {
        ResourceCost resourceCost = loadRecruitableFightAgentsWithCost().get(fightAgent);
        if (resourceCost == null)
        {
            return false;
        }
        
        return resourceCost.isResourceCostCoveredByWallet();
    }
    
    
    @Override
    public void deductCostFromInventory(FightAgent fightAgent)
    {
        ResourceCost resourceCost = loadRecruitableFightAgentsWithCost().get(fightAgent);
        if (resourceCost == null)
        {
            throw new IllegalArgumentException(AGENT_NOT_AVAILABLE);
        }
    }
    
    
    @Override
    public FightAgent changeValue(FightAgent value)
    {
        ActiveGameController.getInstance().get().getPlayer().army().add(value);
        return value;
    }
    
    
    public Faction getFaction()
    {
        return faction;
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
