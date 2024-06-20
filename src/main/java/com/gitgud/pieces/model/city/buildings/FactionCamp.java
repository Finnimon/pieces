package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.model.ResourceCost;
import com.gitgud.pieces.model.gameObjects.Faction;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;
import com.gitgud.pieces.model.player.ResourceType;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


public class FactionCamp extends CityBuilding implements Transactor<FightAgent>
{
    
    
    private static final ResourceCost LEVEL_UP_RESOURCE_COST = new ResourceCost(ResourceType.PLATINUM, 100);
    
    
    private static final Faction DEFAULT_FACTION = Faction.MONOCHROME;
    
    
    private static final String DEFAULT_DESCRIPTION = "Pieces of it's faction can be recruited here.";
    
    
    /**
     * Should Contain the recruitable {@link FightAgent}s   //todo
     */
    private static final String JSON_FILE_PATH = "com\\gitgud\\pieces\\model\\city\\buildings\\FactionCamp.json";
    
    
    private static final String NAME = " Faction Camp";
    
    
    private static final String FIGHT_AGENT_NOT_FOUND_IN_JSON_FILE = "FightAgent not found in JSON File.";
    
    
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
        HashMap<Integer,HashMap<FightAgent, ResourceCost>> recruitableFightAgentsWithCost;
        
        Gson gson = new Gson();
        try
        {
            recruitableFightAgentsWithCost = gson.fromJson(new FileReader(JSON_FILE_PATH), HashMap.class);
        }
        catch (FileNotFoundException e)
        {
            return new HashMap<>();
        }
        
        return recruitableFightAgentsWithCost.get(level);
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
            throw  new IllegalArgumentException(FIGHT_AGENT_NOT_FOUND_IN_JSON_FILE);
        }
    }
    
    
    @Override
    public FightAgent changeValue(FightAgent value)
    {
        ActiveGameController.getInstance().get().getPlayer().army().baseCampStash().get(value.getType()).add(value);
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
