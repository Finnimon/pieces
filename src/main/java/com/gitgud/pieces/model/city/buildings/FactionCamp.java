package com.gitgud.pieces.model.city.buildings;

import com.gitgud.pieces.model.gameObjects.Faction;
import com.gitgud.pieces.model.gameObjects.agents.FightAgent;

import java.util.Collection;
import java.util.HashMap;


public class FactionCamp extends CityBuilding implements Transactor<FightAgent>
{
    private static final Faction DEFAULT_FACTION = Faction.MONOCHROME;
    
    
    private static final String DEFAULT_DESCRIPTION = "Pieces of it's faction can be recruited here.";
    
    
    /**
     * Should Contain the recruitable {@link FightAgent}s   //todo
     */
    private static final String JSON_FILE_PATH = "com\\gitgud\\pieces\\model\\city\\buildings\\FactionCamp.json";
    
    
    private final Faction faction;
    
    
    public FactionCamp(String name, String description, int level, Faction faction)
    {
        super(name, description, level);
        this.faction = faction;
    }
    
    
    public static FactionCamp create(Faction faction, int level)
    {
        return new FactionCamp(faction + "FactionCamp", DEFAULT_DESCRIPTION, level, faction);
    }
    
    
    public static FactionCamp create(Faction faction)
    {
        return create(faction, 1);
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return JSON_FILE_PATH;
    }
    
    
    public HashMap<FightAgent,Integer> loadRecruitableFightAgents()
    {
        return null;
    }//todo
    
    
    @Override
    public boolean isTransactionPossible(FightAgent fightAgent)
    {
        return false;//todo
    }
    
    
    @Override
    public void deductCostFromInventory(FightAgent fightAgent)
    {
        //todo
    }
    
    
    @Override
    public FightAgent changeValue(FightAgent value)
    {
        return null;
    }
}
